package service.impl;

import java.util.ArrayList;
import java.util.List;

import entities.Expense;
import entities.Transaction;
import entities.User;
import enums.SplitType;
import service.SplitService;

public class EqualSplitService implements SplitService {

	@Override
	public List<Transaction> split(Expense expense) {
		double amount = expense.getAmount();
		List<User> participants = expense.getParticipants();
		User paidBy = expense.getPaidBy();
		double split = amount / participants.size();
		split = Math.round(split * 100.0) / 100.0;
		List<Transaction> transactions = new ArrayList<>();
		for (int i = 0; i < participants.size() - 1; i++) {
			transactions.add(new Transaction(participants.get(i), paidBy, split));
			amount -= split;
		}
		transactions.add(new Transaction(participants.get(participants.size() - 1), paidBy, amount));
		return transactions;
	}

}
