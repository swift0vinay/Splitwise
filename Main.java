import java.util.Arrays;

import enums.SplitType;
import exceptions.InvalidSplitException;
import exceptions.InvalidSplitTypeException;
import splitwise.Splitwise;

public class Main {
	public static void main(String[] args) throws InvalidSplitTypeException, InvalidSplitException {
		Splitwise splitwise = new Splitwise();

		int u1 = splitwise.addUser("Vinay", "abc", "1656");

		int gId = splitwise.addGroup("Home");

		splitwise.addUserToGroup(gId, u1);

		int u2 = splitwise.addUser("Soni", "asd", "5521");
		int u3 = splitwise.addUser("Dada", "asd", "5521");

		splitwise.addUserToGroup(gId, u2);
		splitwise.addUserToGroup(gId, u3);

		splitwise.addExpense(gId, "Milk", 100, u1, Arrays.asList(u1, u2, u3), SplitType.EQUAL, null);
		splitwise.addExpense(gId, "Bread", 60, u1, Arrays.asList(u2, u3), SplitType.EQUAL, null);

		splitwise.showBalanceForUser(gId, u1);

		splitwise.addExpense(gId, "Rent", 200, u2, Arrays.asList(u2, u3), SplitType.EXACT,
				new double[] { 150.0, 50.0 });

		splitwise.showBalanceForUser(gId, u1);

		splitwise.showBalanceForUser(gId, u2);

		splitwise.showBalanceForUser(gId, u3);

		splitwise.addExpense(gId, "Juice", 300, u2, Arrays.asList(u1, u2, u3), SplitType.PERCENT,
				new double[] { 25.0, 25.0, 50.0 });

		splitwise.showBalanceForUser(gId, u1);

		splitwise.showBalanceForUser(gId, u2);

		splitwise.showBalanceForUser(gId, u3);
	}
}
