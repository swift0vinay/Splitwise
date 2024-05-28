package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.SplitType;
import exceptions.InvalidSplitException;
import service.SplitService;
import splitwise.Splitwise;

public class Group {
	final private int id;
	private String name;
	private List<User> users;
	private List<Expense> expenses;
	private Map<String, Double> balances;

	public Group(int id, String name) {
		this.id = id;
		this.name = name;
		users = new ArrayList<User>();
		balances = new HashMap<String, Double>();
		expenses = new ArrayList<Expense>();
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addUser(User user) {
		users.add(user);
		System.out.println("Added " + user.getName() + " to " + name);
	}

	public void addExpense(Expense expense, SplitService service) throws InvalidSplitException {
		System.out.println("Adding expense " + expense.getTitle());
		List<Transaction> transactions = service.split(expense);
		expenses.add(expense);
		processTransaction(transactions);
	}

	private void processTransaction(List<Transaction> transactions) {
		System.out.println("-------------------------------");
		transactions.stream().forEach(item -> {
			if (item.getOwer().getId() != item.getLender().getId()) {
				System.out.println(
						item.getOwer().getName() + " owes " + item.getAmount() + "Rs to " + item.getLender().getName());
			}
			balances.put(item.getTid(), balances.getOrDefault(item.getTid(), 0.0) + item.getAmount());
		});
		System.out.println("-------------------------------");
	}

	public void showBalanceForUser(int id) {
		System.out.println("---------- Expense for user " + id + "------------");
		for (String key : balances.keySet()) {
			String[] st = key.split(":");
			int lender = Integer.parseInt(st[0]);
			int ower = Integer.parseInt(st[1]);
			User user = Splitwise.users.get(lender);
			User user2 = Splitwise.users.get(ower);
			if (user.getId() != id && user2.getId() != id) {
				continue;
			}
			if (user.getId() == user2.getId()) {
				continue;
			}
			System.out.println(user2.getName() + " owes " + balances.get(key) + "Rs to " + user.getName());
		}
		System.out.println("-------------------------------");
	}

}
