package com.bprocare.web;



import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bprocare.domain.AlarmGateway;
import com.bprocare.domain.Location;
import com.bprocare.repository.AlarmGatewayRepository;
import com.bprocare.service.AlarmGatewayService;
import com.bprocare.web.dto.AlarmGatewayDto;
import com.bprocare.web.dto.LocationDto;


@RestController
public class AlarmGatewayControllerRest {
		
	@Autowired
	AlarmGatewayRepository alarmGatewayRepository;

	@Autowired
	AlarmGatewayService alarmGatewayService;	
	
    @Autowired
    private ModelMapper modelMapper;
	
    // Cors Enabled
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/alarmGateways", method = RequestMethod.GET)
    public List<AlarmGateway> getAllAlarmGateways() {
    	
    	return (List<AlarmGateway>) alarmGatewayRepository.findAll();
    } 
    
    // Cors Enabled
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/alarmGateways/{id}", method = RequestMethod.GET)
    public AlarmGateway getAlarmGateway(@PathVariable Long id) {
    	
    	return alarmGatewayService.getAlarmGateway(id);
    }      
    
    // Cors Enabled   
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/alarmGateways/{id}")
    public AlarmGateway delete(@PathVariable Long id) {
    	
      AlarmGateway alarmGatewayFromDB = alarmGatewayService.getAlarmGateway(id);	
    	
      try {
    	  
    	alarmGatewayService.deleteAlarmGateway(id);
      } catch (Exception e)
      { 
    	  
    	  return null;
      }
      return alarmGatewayFromDB;
    }    
 
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/alarmGateways")
    public ResponseEntity<Object> saveAlarmGateway(@RequestBody @Valid AlarmGatewayDto alarmGatewaydto, BindingResult result){
    	
    	System.out.println("TEST1");
    	
    	AlarmGateway alarmGatewayToDB = modelMapper.map(alarmGatewaydto, AlarmGateway.class);
    	
    	if (result.hasErrors())
    	{
    		 		
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    	
    	alarmGatewayService.createAlarmGateway(alarmGatewayToDB);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }    
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/alarmGateways/{id}")
    public ResponseEntity<Object> updateAlarmGateway(@RequestBody @Valid AlarmGatewayDto alarmGatewaydto, BindingResult result) {
    	
    	AlarmGateway alarmGatewayToDB = modelMapper.map(alarmGatewaydto, AlarmGateway.class);
    	
    	if (result.hasErrors())
    	{
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
                  return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    	   	
    	alarmGatewayService.changeAlarmGateway(alarmGatewayToDB);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }        
    
    
    
    
    
    @RequestMapping(value = "/data/alarmGateways", method = RequestMethod.GET)
    public DataTablesOutput<AlarmGateway> getLocations(DataTablesInput input) {
    	
      return alarmGatewayRepository.findAll(input);
    }   
	
}
