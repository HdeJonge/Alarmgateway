package nl.bprocare.alarmgateway.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.bprocare.alarmgateway.dto.LocationDto;
import nl.bprocare.alarmgateway.repository.LocationDataTableRepository;

@RestController
public class LocationRestController {
	@Autowired
	private LocationDataTableRepository repository;
	
	@GetMapping("/restlocations")
	public DataTablesOutput<LocationDto> getLocations(@Valid DataTablesInput input){
		return repository.findAll(input);
	}
	
}
