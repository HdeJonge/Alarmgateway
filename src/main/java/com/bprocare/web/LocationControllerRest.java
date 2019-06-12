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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bprocare.domain.Label;
import com.bprocare.domain.Location;
import com.bprocare.repository.LabelRepository;
import com.bprocare.repository.LocationRepository;
import com.bprocare.service.LocationService;
import com.bprocare.web.dto.LocationDto;
import com.bprocare.web.dto.LocationDtoChange;
import com.bprocare.websockets.MessageHandler;
import com.bprocare.domain.ApiResponse;


@RestController
public class LocationControllerRest {

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	LocationService locationService;	
	
    @Autowired
    private ModelMapper modelMapper;
	
    @Autowired
    private MessageHandler messageHandler;
    
    // Cors Enabled
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public List<Location> getAllLocation() {
    	
    	return (List<Location>) locationRepository.findAll();
    }     
    
    // Cors Enabled
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
    public Location getLocation(@PathVariable Long id) {
    	
    	return locationService.getLocation(id);
    }   
    
    // Cors Enabled 
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
    	
      Location locationFromDB = locationService.getLocation(id);
    	
      try {
    	locationService.deleteLocation(id);
      } catch (Exception e)
      { 
    	  
    	  return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
      }
      return new ResponseEntity<>(HttpStatus.CREATED);
    }      
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/locations")
    public ResponseEntity<Object> saveLocation(@RequestBody @Valid LocationDto locationdto, BindingResult result){
    	
    	Location locationToDB = modelMapper.map(locationdto, Location.class);
    	
    	if (result.hasErrors())
    	{
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
                  return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    	   	
        locationService.createLocation(locationToDB);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }    
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/locations/{id}")
    public ResponseEntity<Object> updateLocation(@RequestBody @Valid LocationDtoChange locationdtochange, BindingResult result) {
    	   	
    	Location locationToDB = modelMapper.map(locationdtochange, Location.class);
    	
    	if (result.hasErrors())
    	{
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
                      
            return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    		
        locationService.changeLocation(locationToDB);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }    
    
    
   
    
    
    
    
    
    @RequestMapping(value = "/data/locations", method = RequestMethod.GET)
    public DataTablesOutput<Location> getLocations(DataTablesInput input) {
    	    	
        return locationRepository.findAll(input);
    }
    
    @RequestMapping(value = "/data/labels", method = RequestMethod.GET)
    public List<Label> getLocations() {
    	    	    	
    	List<Label> labelLst= (List<Label>) labelRepository.findAll();
    	
        return (List<Label>) labelLst;
    }

    @RequestMapping(value = "/data/message", method = RequestMethod.GET)
    public void sendMessage(@RequestParam("msg") String message) {
    	    	    	
    	messageHandler.sendMessageToAll(message);
    }
    
    
}
