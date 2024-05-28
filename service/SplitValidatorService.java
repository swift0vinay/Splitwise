package service;

import enums.SplitType;
import exceptions.InvalidSplitException;

public interface SplitValidatorService extends SplitService {
	void validate(double amount, SplitType splitType, double[] splits) throws InvalidSplitException;
}
