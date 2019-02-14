package nl.bprocare.alarmgateway.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.bprocare.alarmgateway.domain.Label;
import nl.bprocare.alarmgateway.repository.LabelRestRepository;

@RestController
public class LabelRestController {
	@Autowired
	private LabelRestRepository repository;
	
	@GetMapping("/restlabels")
	public DataTablesOutput<Label> getLabels(@Valid DataTablesInput input){
		return repository.findAll(input);
	}
}
