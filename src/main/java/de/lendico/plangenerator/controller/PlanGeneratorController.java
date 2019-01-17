package de.lendico.plangenerator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.lendico.plangenerator.pojo.Installment;
import de.lendico.plangenerator.pojo.LoanSpecs;
import de.lendico.plangenerator.service.GeneratorPlanServiceImpl;

/**
 * A web controller for generating installment plans.
 * 
 * @author Abdulrahman Rabie
 *
 */
@RestController
public class PlanGeneratorController {

	@Autowired
	private GeneratorPlanServiceImpl genPlanService ;
	
	@PostMapping(path = "/generate-plan")
	public ResponseEntity<List<Installment>> generatePlan(@RequestBody LoanSpecs loanSpecs){
		List<Installment> installments = new ArrayList<>();
		return ResponseEntity.ok(genPlanService.generatePlan(loanSpecs, installments));
	}
}
