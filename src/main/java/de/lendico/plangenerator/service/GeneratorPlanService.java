package de.lendico.plangenerator.service;

import java.util.List;
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
	 * @param installments
	 * @return list of all repayment/installment option report for a plan.
	 */
	public List<Installment> generatePlan(LoanSpecs loanSpecs, List<Installment> installments);

}
