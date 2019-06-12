package com.bprocare.web;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.bprocare.domain.AlarmGateway;
import com.bprocare.service.AlarmGatewayService;
import com.bprocare.service.LocationService;
import com.bprocare.web.dto.AlarmGatewayDto;
import com.bprocare.web.dto.AlarmGatewayDtoChange;


@Controller
public class AlarmGatewayController {
	
	public AlarmGatewayController( ) {
		
	}
	
    @Autowired
    AlarmGatewayService alarmGatewayService;
		
    @Autowired
    LocationService locationService;    
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/")
    public String main(Model model) {
        return "index"; 
    }

    @GetMapping("/alarmGateway")
    public String getAllAlarmGateway(Model model) {
    	      
        return "alarmGateway";
    } 
    
    @GetMapping("/alarmGateway/add")
    public String addAlarmGateway(Model model) {
        
        model.addAttribute("alarmGateway",new AlarmGatewayDto());
        model.addAttribute("locations", locationService.getAllLocations());
        
        return "alarmGateway_add";
    }      
    
    @GetMapping("/alarmGateway/edit/{id}")
    public String addAlarmGateway(@PathVariable("id") Long id, Model model) {
        
    	AlarmGatewayDto alarmGatewayDto = new AlarmGatewayDto();
    	
    	AlarmGateway alarmGatewayFromDb = (AlarmGateway)alarmGatewayService.getAlarmGateway(id);
    	alarmGatewayDto = modelMapper.map(alarmGatewayFromDb, AlarmGatewayDto.class);		
    			
        model.addAttribute("alarmGateway",alarmGatewayDto);
        model.addAttribute("locations", locationService.getAllLocations());
        
        return "alarmGateway_change";
    }    
    
    
    // create location
    @PostMapping("/addAlarmGateway")
    public String createLocation(@Valid @ModelAttribute("alarmGateway") AlarmGatewayDto alarmGatewaydto, BindingResult result, Model model) {
  	
        if (result.hasErrors()) {
        	model.addAttribute("locations", locationService.getAllLocations());
        	return "alarmGateway_add";
        }       	
    	
        AlarmGateway alarmGatewayToDb = modelMapper.map(alarmGatewaydto, AlarmGateway.class);
        alarmGatewayService.createAlarmGateway(alarmGatewayToDb);
    	
    	model.addAttribute("alarmGateways",alarmGatewayService.getAllAlarmGateways());
  	    	
    	return "redirect:/alarmGateway";
    }    
    
    
    // change location
    @PostMapping("/changeAlarmGateway/{id}")
    public String changeLocation(
    		@PathVariable("id") Long id,
    		@Valid @ModelAttribute("alarmGateway") AlarmGatewayDtoChange alarmGatewaydtochange, BindingResult result, Model model) {

        if (result.hasErrors()) {
        	return "alarmGateway_change";
        }     	
        
        AlarmGateway alarmGatewayToDb = modelMapper.map(alarmGatewaydtochange, AlarmGateway.class);
    	
        alarmGatewayService.changeAlarmGateway(alarmGatewayToDb);
    	model.addAttribute("alarmGateways", alarmGatewayService.getAllAlarmGateways());
    	
    	return "redirect:/alarmGateway"; 
    }    
    

    // delete location
    @GetMapping("/alarmGateway/delete/{id}")
    public String deleteLocation(
    		@PathVariable("id") Long id, Model model ) {
    	
    	try {
    	  alarmGatewayService.deleteAlarmGateway(id);
    	} catch (Exception ex)
    	{
    		model.addAttribute("error", "Kan alarm gateway niet vinden");
    		return "alarmGateway";
    	}

        model.addAttribute("alarmGateways", alarmGatewayService.getAllAlarmGateways());
        return "redirect:/alarmGateway";
    }      
	
}
