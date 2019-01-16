package de.lendico.plangenerator.service;

import java.time.LocalDateTime;
import java.util.Optional;

import de.lendico.plangenerator.pojo.Installment;
import de.lendico.plangenerator.pojo.LoanSpecs;

/**
 * @author Abdulrahman Rabie
 */
@FunctionalInterface
public interface RepaymentHelperService {

	/**
	 * Helper Method to calculate the annuity payment or installment interest rate.
	 * 
     * @param loanAmount total amount of the loan.
     * @param rate nominal/monthly rate.
     * @param duration optional parameter that represent the loan installment duration.
     * 
     * @return double value to represent AnnuityPayment or interest rate
     */
	double specsCalculation(double loanAmount, double rate, Optional<Integer> duration);
	
	/**
	 * Helper Method to Update Loan Specifications for the next month.
	 * 
     * @param loanSpecs all the loan specifications
     * @param remainingAmount remaining amount after (n - 1) of installment 
     */
	static void editLoanSpecs(LoanSpecs loanSpecs, double remainingAmount) {
		loanSpecs.setStartDate(loanSpecs.getStartDate().plusMonths(1));
		loanSpecs.setDuration(loanSpecs.getDuration() - 1);
		loanSpecs.setLoanAmount(remainingAmount);
	}

	/**
	 * Helper Method to create installment plan for a month
	 * 
     * @param date Installment due date.
     * @param annuityPayment periodic payment on an annuity.
     * @param principal base amount after subtracting interest from annuity.
     * @param installmentInterest amount of interest rate.
     * @param loanAmount initial outstanding principal.
     * @param remainingAmount remaining initial outstanding principal after subtracting annuity payment.
     * 
     * @return Installment value to represent AnnuityPayment or interest rate
     */
	static Installment createMonthInstallment(LocalDateTime date, double annuityPayment, double principal,
			double installmentInterest, double loanAmount, double remainingAmount) {
		Installment installment = new Installment();
		installment.setBorrowerPaymentAmount(annuityPayment);
		installment.setDate(date);
		installment.setInitialOutstandingPrincipal(loanAmount);
		installment.setInterest(installmentInterest);
		installment.setPrincipal(principal);
		installment.setRemainingOutstandingPrincipal(remainingAmount);

		return installment;
	}


}
