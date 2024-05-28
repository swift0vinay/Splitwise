package splitwise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.Expense;
import entities.Group;
import entities.User;
import enums.SplitType;
import exceptions.InvalidSplitException;
import exceptions.InvalidSplitTypeException;
import service.SplitService;
import service.impl.EqualSplitService;
import service.impl.ExactSplitService;
import service.impl.PercentSplitService;

public class Splitwise {

	List<Group> groups;
	public static List<User> users;
	static {
		users = new ArrayList<User>();
		users.add(null);
	}

	public Splitwise() {
		this.groups = new ArrayList<>();
		groups.add(null);
	}

	public int addGroup(String name) {
		int id = groups.size();
		Group group = new Group(id, name);
		groups.add(group);
		System.out.println("Created group " + name);
		return group.getId();
	}

	public int addUser(String name, String email, String mobile) {
		int id = users.size();
		User user = new User(id, name, email, mobile);
		users.add(user);
		System.out.println("Created user " + name);
		return user.getId();
	}

	public void addUserToGroup(int groupId, int userId) {
		groups.get(groupId).addUser(users.get(userId));
	}

	public SplitService getSplitService(SplitType splitType) throws InvalidSplitTypeException {
		switch (splitType) {
		case EQUAL:
			return new EqualSplitService();
		case EXACT:
			return new ExactSplitService();
		case PERCENT:
			return new PercentSplitService();
//		case SHARE:
//			return new ShareSplit();
//		}
		}
		throw new InvalidSplitTypeException();
	}

	public void addExpense(int groupId, String title, double amount, int paidBy, List<Integer> participants,
			SplitType splitType, double splits[]) throws InvalidSplitTypeException, InvalidSplitException {
		Group group = groups.get(groupId);
		User paidByUser = users.get(paidBy);
		List<User> participantUsers = participants.stream().map(i -> users.get(i)).collect(Collectors.toList());
		Expense expense = new Expense(title, paidByUser, amount, participantUsers, splitType, splits);
		SplitService service = getSplitService(splitType);
		group.addExpense(expense, service);
	}

	public void showBalanceForUser(int groupId, int userId) {
		Group group = groups.get(groupId);
		group.showBalanceForUser(userId);
	}
}
