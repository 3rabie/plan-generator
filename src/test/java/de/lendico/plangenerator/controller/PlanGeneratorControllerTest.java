package de.lendico.plangenerator.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.lendico.plangenerator.pojo.Installment;
import de.lendico.plangenerator.pojo.LoanSpecs;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlanGeneratorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private LoanSpecs loanSpecs;
	private Installment installment;
	private List<Installment> instalments;
	private String jsonReq;

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

		jsonReq = "{  \"loanAmount\": \"5000\",  \"nominalRate\": \"5.0\",  \"duration\": 24,  \"startDate\": \"2018-01-01T00:00:01Z\" } ";
	}

	@Test
	public void planGeneratorHappyScenarioTest() throws Exception {
		this.mockMvc.perform(post("/generate-plan").contentType(MediaType.APPLICATION_JSON).content(jsonReq)
				.characterEncoding("utf-8")).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void planGeneratorBadScenarioTest() throws Exception {
		jsonReq = "";

		this.mockMvc.perform(post("/generate-plan").contentType(MediaType.APPLICATION_JSON).content(jsonReq)
				.characterEncoding("utf-8")).andExpect(status().isBadRequest()).andReturn();
	}
}
