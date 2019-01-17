package de.lendico.plangenerator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.lendico.plangenerator.pojo.Constant;
import de.lendico.plangenerator.pojo.Installment;
import de.lendico.plangenerator.pojo.LoanSpecs;

/**
 * This Class used as the plan factory to generate the installments / repayment plan.
 * 
 * @author Abdulrahman Rabie
 *
 */
@Service
public class GeneratorPlanServiceImpl implements GeneratorPlanService {

	@Override
	public List<Installment> generatePlan(LoanSpecs loanSpecs, List<Installment> installments) {
		
		// get the monthly rate by dividing the nominal rate by 12 and 100 as it is in %
		double ratePerMonth = (loanSpecs.getNominalRate() / 12) / 100;

		// Annuity Payment Calculation
		BigDecimal annuityPayment = calcAnnuityPayment(loanSpecs.getLoanAmount(), ratePerMonth,
				loanSpecs.getDuration());

		// Installment Interest Calculations
		BigDecimal installmentInterest = calcInstallmentInterest(loanSpecs.getLoanAmount(), loanSpecs.getNominalRate());

		// Principal = Annuity - Interest
		BigDecimal principal = annuityPayment.subtract(installmentInterest).setScale(2, BigDecimal.ROUND_HALF_EVEN);

		// remainingPrincipal = initialPrincipal - monthlyPrincipal
		BigDecimal remainingAmount = BigDecimal.valueOf(loanSpecs.getLoanAmount()).subtract(principal).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);

		// insure that the remaining amount to not be negative
		remainingAmount = (remainingAmount.doubleValue() >= 0) ? remainingAmount : BigDecimal.valueOf(0);

		// Create installment/repayment for a month
		Installment installmentForMonth = RepaymentHelperService.createMonthInstallment(loanSpecs.getStartDate(),
				annuityPayment.doubleValue(), principal.doubleValue(), installmentInterest.doubleValue(),
				loanSpecs.getLoanAmount(), remainingAmount.doubleValue());

		// Add the Installment/repayment to Installment Report
		installments.add(installmentForMonth);

		// Update the Loan Specs by updating the Initial Loan Amount And Duration and
		// Month Date
		RepaymentHelperService.editLoanSpecs(loanSpecs, remainingAmount.doubleValue());

		if (loanSpecs.getLoanAmount() == 0)
			return installments;
		else {
			return generatePlan(loanSpecs, installments);
		}
	}
	
	/*-----------------------------------------------------------------------*
	 *-------------------| Annuity Payment Calculation |---------------------*
	 *                    -----------------------------                      *
	 *                                                                       *
	 * 						       Loan Amount * rate per period             *
	 * annuity payment = --------------------------------------------------  * 
	 * 						1 - ( 1 + rate per period ) power ( - duration)  *
	 *                                                                       *
	 * For further explanation :-                                            *
	 * Please visit http://financeformulas.net/Annuity_Payment_Formula.html  *
	 *-----------------------------------------------------------------------*/
	private BigDecimal calcAnnuityPayment(double initialLoanAmount, double ratePerMonth, Integer installemntDuration) {
		RepaymentHelperService calcAnnuityPayment = (double loanAmount, double rate,
				Optional<Integer> duration) -> ((loanAmount * rate) / (1 - Math.pow((1 + rate), -duration.get())));

		return RepaymentHelperService.convertToBigDecimal(
				calcAnnuityPayment.specsCalculation(initialLoanAmount, ratePerMonth, Optional.of(installemntDuration)));
	}
	
	/*--------------------------------------------------------------------------*
	 *-----------------------| Interest Rate Calculation |----------------------*
	 *                        ---------------------------                       *
	 *           (Nominal-Rate * Days in Month * Initial Outstanding Principal) *
	 * Interest = ------------------------------------------------------------- *     
	 *                                  days in year                            *
	 *--------------------------------------------------------------------------*/
	private BigDecimal calcInstallmentInterest(double initialLoanAmount, double nominalRate) {
		RepaymentHelperService calcInstallmentInterest = (double loanAmount, double rate,
				Optional<Integer> duration) -> ((rate * Constant.DAYS_IN_MONTH * loanAmount) / Constant.DAYS_IN_YEAR);

		return RepaymentHelperService.convertToBigDecimal(calcInstallmentInterest
				.specsCalculation(initialLoanAmount, nominalRate, Optional.empty()) / 100);
	}
	
}
