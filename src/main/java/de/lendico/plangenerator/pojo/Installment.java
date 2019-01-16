package de.lendico.plangenerator.pojo;

import java.time.LocalDateTime;

/**
 * Class that represents a Installment/Repayment.
 * 
 * @author Abdulrahman Rabie
 *
 */
public class Installment {

	private double borrowerPaymentAmount;
	private LocalDateTime date;
	private double initialOutstandingPrincipal;
	private double interest;
	private double principal;
	private double remainingOutstandingPrincipal;
	
	/**
	 * @return borrowerPaymentAmount
	 */
	public double getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}

	/**
	 * @param borrowerPaymentAmount
	 */
	public void setBorrowerPaymentAmount(double borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}

	/**
	 * @return date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * @return initialOutstandingPrincipal
	 */
	public double getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}

	/**
	 * @param initialOutstandingPrincipal
	 */
	public void setInitialOutstandingPrincipal(double initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}

	/**
	 * @return Installment Interest
	 */
	public double getInterest() {
		return interest;
	}

	/**
	 * @param Installment Interest
	 */
	public void setInterest(double interest) {
		this.interest = interest;
	}

	/**
	 * @return principal
	 */
	public double getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 */
	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	/**
	 * @return remainingOutstandingPrincipal
	 */
	public double getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}

	/**
	 * @param remainingOutstandingPrincipal
	 */
	public void setRemainingOutstandingPrincipal(double remainingOutstandingPrincipal) {
		this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\r\n");
		builder.append("[borrowerPaymentAmount=");
		builder.append(borrowerPaymentAmount);
		builder.append(", date=");
		builder.append(date);
		builder.append(", initialOutstandingPrincipal=");
		builder.append(initialOutstandingPrincipal);
		builder.append(", interest=");
		builder.append(interest);
		builder.append(", principal=");
		builder.append(principal);
		builder.append(", remainingOutstandingPrincipal=");
		builder.append(remainingOutstandingPrincipal);
		builder.append("]");
		return builder.toString();
	}
}
