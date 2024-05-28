package service;

import java.util.List;

import entities.Expense;
import entities.Transaction;
import entities.User;
import enums.SplitType;
import exceptions.InvalidSplitException;

public interface SplitService {

	List<Transaction> split(Expense expense) throws InvalidSplitException;

}
