package entities;

import java.util.List;
import java.util.UUID;

import enums.SplitType;

public class Expense {
	private final UUID id;
	private String title;
	private User paidBy;
	private double amount;
	private List<User> participants;
	private SplitType splitType;
	private double splits[];

	public Expense(String title, User paidBy, double amount, List<User> participants, SplitType splitType,
			double splits[]) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.paidBy = paidBy;
		this.amount = amount;
		this.participants = participants;
		this.splitType = splitType;
		this.splits = splits;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public SplitType getSplitType() {
		return splitType;
	}

	public void setSplitType(SplitType splitType) {
		this.splitType = splitType;
	}

	public double[] getSplits() {
		return splits;
	}

	public void setSplits(double[] splits) {
		this.splits = splits;
	}

}
