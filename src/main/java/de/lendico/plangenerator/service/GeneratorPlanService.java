package de.lendico.plangenerator.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import de.lendico.plangenerator.pojo.Constant;
import de.lendico.plangenerator.pojo.Installment;
import de.lendico.plangenerator.pojo.LoanSpecs;

/**
 * 
 * @author Abdulrahman Rabie
 * 
 */
public interface GeneratorPlanService {

	/**
	 * 
	 * @param loanSpecs
	 *            loan specifications.
	 * @return list of all repayment/installment option for a plan.
	 */
	public List<Installment> planGenerator(LoanSpecs loanSpecs);

	/**
	 * 
	 * @param loanSpecs
	 *            loan specifications.
	 * @param installments
	 * @return list of all repayment/installment option report for a plan.
	 */
	default List<Installment> installmentReport(LoanSpecs loanSpecs, List<Installment> installments) {

		// get the monthly rate by dividing the nominal rate by 12 and 100 as it is in % factor
		double ratePerMonth = (loanSpecs.getNominalRate() / 12) / 100;

		/*-------------------------------------------------------------------*/
		/*------------------ Annuity Payment Calculation --------------------*/
		/*-------------------------------------------------------------------*/
		/*
		 * Please visit http://financeformulas.net/Annuity_Payment_Formula.html for further explanation
		 * 						Loan Amount * rate per period
		 * annuity payment = -----------------------------------
		 * 						1 - ( 1 + rate per period ) power ( - duration)
		 */
		RepaymentHelperService calcAnnuityPayment = (double loanAmount, double rate,
				Optional<Integer> duration) -> ((loanAmount * rate) / (1 - Math.pow((1 + rate), -duration.get())));
		BigDecimal annuityPayment = BigDecimal.valueOf(calcAnnuityPayment.specsCalculation(loanSpecs.getLoanAmount(),
				ratePerMonth, Optional.of(loanSpecs.getDuration()))).setScale(2, BigDecimal.ROUND_HALF_EVEN);

		/*-------------------------------------------------------------------*/
		/*-------------------- Interest Rate Calculation --------------------*/
		/*-------------------------------------------------------------------*/
		/*
		 * Interest = (Nominal-Rate * Days in Month * Initial Outstanding Principal) / days in year 
		 */
		RepaymentHelperService calcInstallmentInterest = (double loanAmount, double rate,
				Optional<Integer> duration) -> ((rate * Constant.DAYS_IN_MONTH * loanAmount) / Constant.DAYS_IN_YEAR);
		BigDecimal installmentInterest = BigDecimal.valueOf(calcInstallmentInterest
				.specsCalculation(loanSpecs.getLoanAmount(), loanSpecs.getNominalRate(), Optional.empty()) / 100)
				.setScale(2, BigDecimal.ROUND_HALF_EVEN);// per EURO

		/*-------------------------------------------------------------------*/
		/*--------------------- Principal  Calculation ----------------------*/
		/*-------------------------------------------------------------------*/
		/*
		 * Principal = Annuity - Interest
		 */
		BigDecimal principal = annuityPayment.subtract(installmentInterest).setScale(2, BigDecimal.ROUND_HALF_EVEN);

		
		// then calculate the remaining amount by subtracting principal from initial loan amount 
		BigDecimal remainingAmount = BigDecimal.valueOf(loanSpecs.getLoanAmount()).subtract(principal).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);

		LocalDateTime date = loanSpecs.getStartDate();

		// insure that the remaining amount to not be negative
		remainingAmount = (remainingAmount.doubleValue() >= 0) ? remainingAmount : BigDecimal.valueOf(0);

		Installment installmentForMonth = RepaymentHelperService.createMonthInstallment(date,
				annuityPayment.doubleValue(), principal.doubleValue(), installmentInterest.doubleValue(),
				loanSpecs.getLoanAmount(), remainingAmount.doubleValue());

		installments.add(installmentForMonth);

		RepaymentHelperService.editLoanSpecs(loanSpecs, remainingAmount.doubleValue());

		if (loanSpecs.getLoanAmount() == 0)
			return installments;
		else {
			return installmentReport(loanSpecs, installments);
		}
	}

}
