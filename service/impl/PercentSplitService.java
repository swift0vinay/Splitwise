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

public class PercentSplitService implements SplitValidatorService {

	@Override
	public List<Transaction> split(Expense expense) throws InvalidSplitException {
		double amount = expense.getAmount();
		double tmp = expense.getAmount();
		List<User> participants = expense.getParticipants();
		User paidBy = expense.getPaidBy();
		double splits[] = expense.getSplits();
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int i = 0; i < participants.size() - 1; i++) {
			double curr = (amount * splits[i]) / 100.0;
			curr = Math.round(curr * 100.0) / 100.0;
			tmp -= curr;
			transactions.add(new Transaction(participants.get(i), paidBy, curr));
		}
		transactions.add(new Transaction(participants.get(participants.size() - 1), paidBy, tmp));
		return transactions;
	}

	@Override
	public void validate(double amount, SplitType splitType, double[] splits) throws InvalidSplitException {
		double total = Arrays.stream(splits).sum();
		if (total != 100.0) {
			throw new InvalidSplitException("Split amount does not sum up to 100%");
		}
	}

}
