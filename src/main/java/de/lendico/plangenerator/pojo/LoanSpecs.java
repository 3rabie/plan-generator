package de.lendico.plangenerator.pojo;


import java.time.LocalDateTime;

/**
 * Class that represents a Loan Specifications.
 * 
 * @author Abdulrahman Rabie
 *
 */
public class LoanSpecs {

	private double loanAmount;
	private double nominalRate;
	private int duration;
	private LocalDateTime startDate;
	/**
	 * @return the loanAmount
	 */
	public double getLoanAmount() {
		return loanAmount;
	}
	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	/**
	 * @return the nominalRate
	 */
	public double getNominalRate() {
		return nominalRate;
	}
	/**
	 * @param nominalRate the nominalRate to set
	 */
	public void setNominalRate(double nominalRate) {
		this.nominalRate = nominalRate;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the startDate
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	

}