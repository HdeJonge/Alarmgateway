package nl.bprocare.alarmgateway.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import nl.bprocare.alarmgateway.pojo.Alarmgateway;
import nl.bprocare.alarmgateway.repository.AlarmgatewayDataTableRepository;

@RestController
public class AlarmgatewayRestController {
	@Autowired
	private AlarmgatewayDataTableRepository repository;
	
	@GetMapping("/restgateways")
	public DataTablesOutput<Alarmgateway> getAlarmgateways(@Valid DataTablesInput input){
		return repository.findAll(input);
	}

}
