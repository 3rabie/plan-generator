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

	@Autowired
	private GeneratorPlanService generatorPlanService;

	private LoanSpecs loanSpecs;
	private Installment installment;
	private List<Installment> instalments;

	@Before
	public void setup() {

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
	public void planGeneratorHappyScenarioTest() {
		List<Installment> installments = generatorPlanService.generatePlan(loanSpecs, new ArrayList<>());

		Assert.assertEquals(installments.get(0).toString(), instalments.get(0).toString());
	}

	@Test
	public void planGeneratorBadScenarioTest() {
		instalments.remove(0);
		installment = new Installment();
		installment.setBorrowerPaymentAmount(319.36);
		installment.setDate(LocalDateTime.of(2018, 2, 1, 00, 00, 00, 01));
		installment.setInitialOutstandingPrincipal(5000);
		installment.setInterest(60.83);
		installment.setPrincipal(298.53);
		installment.setRemainingOutstandingPrincipal(4701.47);
		instalments.add(installment);
		List<Installment> installments = generatorPlanService.generatePlan(loanSpecs, new ArrayList<>());

		Assert.assertNotEquals(installments.get(0).toString(), instalments.get(0).toString());
	}
}
