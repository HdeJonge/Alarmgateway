package com.bprocare.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bprocare.domain.Location;
import com.bprocare.service.LocationService;
import com.bprocare.web.dto.LocationDto;
import com.bprocare.web.dto.LocationDtoChange;

@Component
public class PostalCodeValidator implements ConstraintValidator<ValidPostalCode, Object> {

	public PostalCodeValidator() {
		
	}

	private boolean isUpdate;
	
	@Autowired
	LocationService locationService;
	
    @Autowired
    private ModelMapper modelMapper;
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintContext) {
	    	
		if (!isUpdate)
		{	
		   // create new location				
	       LocationDto locationdto = (LocationDto) value;
		   Location location = modelMapper.map(locationdto, Location.class);
	       return locationService.checkpostalcode(location);
	       
		}  
		else
		{
		   // update location		
		   LocationDtoChange locationdtochange = (LocationDtoChange) value;
		   Location location = modelMapper.map(locationdtochange, Location.class);
		   
		   // Check if postalcode and housenumber are the same as in DB
		   if (locationService.comparePostalAndHousenum(location))
		     return true;
		   else		   
		     return locationService.checkpostalcode(location);			
		}
	}

	@Override
	public void initialize(ValidPostalCode constraintAnnotation) {
		
		isUpdate = constraintAnnotation.isUpdate();
	}
	
}
