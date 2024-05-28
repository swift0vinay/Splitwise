package service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Expense;
import entities.Transaction;
import entities.User;
import enums.SplitType;
import exceptions.InvalidSplitException;
import service.SplitValidatorService;

public class ExactSplitService implements SplitValidatorService {

	@Override
	public List<Transaction> split(Expense expense) throws InvalidSplitException {
		double amount = expense.getAmount();
		List<User> participants = expense.getParticipants();
		User paidBy = expense.getPaidBy();
		double splits[] = expense.getSplits();
		validate(amount, expense.getSplitType(), splits);
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int i = 0; i < participants.size(); i++) {
			transactions.add(new Transaction(participants.get(i), paidBy, splits[i]));
		}
		return transactions;
	}

	@Override
	public void validate(double amount, SplitType splitType, double[] splits) throws InvalidSplitException {
		double total = Arrays.stream(splits).sum();
		if (total != amount) {
			throw new InvalidSplitException("Split amount does not sum up to total amount");
		}
	}

}
