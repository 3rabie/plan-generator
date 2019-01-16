package de.lendico.plangenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.lendico.plangenerator.pojo.Installment;
import de.lendico.plangenerator.pojo.LoanSpecs;
import de.lendico.plangenerator.service.GeneratorPlanServiceImpl;

@RestController
public class PlanGeneratorController {

	@Autowired
	private GeneratorPlanServiceImpl genPlanService ;
	
	@PostMapping(path = "/generate-plan")
	public ResponseEntity<List<Installment>> generatePlan(@RequestBody LoanSpecs loanSpecs){
		return ResponseEntity.ok(genPlanService.planGenerator(loanSpecs));
	}
}
