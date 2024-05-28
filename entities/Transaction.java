package entities;

public class Transaction {

	private User ower;
	private User lender;
	private double amount;

	public Transaction(User ower, User lender, double amount) {
		super();
		this.ower = ower;
		this.lender = lender;
		this.amount = amount;
	}

	public User getOwer() {
		return ower;
	}

	public User getLender() {
		return lender;
	}

	public double getAmount() {
		return amount;
	}

	public String getTid() {
		return lender.getId() + ":" + ower.getId();
	}

}
