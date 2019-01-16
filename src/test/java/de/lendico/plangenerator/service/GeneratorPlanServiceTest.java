package de.lendico.plangenerator.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.lendico.plangenerator.pojo.Installment;
import de.lendico.plangenerator.pojo.LoanSpecs;

@ActiveProfiles("unit")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GeneratorPlanServiceTest {
	
	private GeneratorPlanService generatorPlanService;
	
	private LoanSpecs loanSpecs ;
	private Installment installment ;
	private List<Installment> instalments ;
	@Before
	public void setup() {
		generatorPlanService = new GeneratorPlanServiceImpl();
		
		loanSpecs = new LoanSpecs();
		loanSpecs.setLoanAmount(5000);
		loanSpecs.setNominalRate(5);
		loanSpecs.setDuration(24);
		loanSpecs.setStartDate(LocalDateTime.of(2018, 1, 1, 00, 00, 00, 01));
		
		installment = new Installment();
		installment.setBorrowerPaymentAmount(219.36);
		installment.setDate(LocalDateTime.of(2018, 1, 1, 00, 00, 00, 01));
		installment.setInitialOutstandingPrincipal(5000);
		installment.setInterest(20.83);
		installment.setPrincipal(198.53);
		installment.setRemainingOutstandingPrincipal(4801.47);
		
		instalments = new ArrayList<>();
		instalments.add(installment);
		



	}
	
	@Test
	public void planGeneratorTest() {
		List<Installment> installments = generatorPlanService.planGenerator(loanSpecs);
		
		Assert.assertEquals(installments.get(0).toString(), instalments.get(0).toString());
	}
	
	@Test
	public void installmentsReport() {
		List<Installment> installments = new ArrayList<>();
		installments = generatorPlanService.installmentReport(loanSpecs, installments);
		
		Assert.assertEquals(installments.get(0).toString(), instalments.get(0).toString());
	}
}
