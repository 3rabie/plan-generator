package de.lendico.plangenerator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
	public List<Installment> planGenerator(LoanSpecs loanSpecs) {
		List<Installment> installments = new ArrayList<>();

		return installmentReport(loanSpecs, installments);
	}
	
}
