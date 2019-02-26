package constraints;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import nl.bprocare.alarmgateway.dto.EditLocationDTO;
import nl.bprocare.alarmgateway.pojo.Location;
import nl.bprocare.alarmgateway.service.LocationService;

public class UniqueLocationValidator implements ConstraintValidator<UniqueLocation, Object> {

	private String id;
	private String postalCode;
	private String streetNumber;
	
	@Autowired
	private LocationService service;

	public UniqueLocationValidator() {
	}
	
	@Override
	public void initialize(UniqueLocation uniqueLocation) {
		this.id = uniqueLocation.id();
		this.postalCode = uniqueLocation.postalCode();
		this.streetNumber = uniqueLocation.streetNumber();
	}
	/*
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		Object postalCodeVal = new BeanWrapperImpl(object).getPropertyValue(postalCode);
		Object streetNumberVal = new BeanWrapperImpl(object).getPropertyValue(streetNumber);
		List<Location> locationByPostalCodeAndStreetNumber = service.getLocationByPostalCodeAndStreetNumber(postalCodeVal.toString(), streetNumberVal.toString());
		return locationByPostalCodeAndStreetNumber.isEmpty();
		
		//return true;
	}
	*/
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		Object idVal = new BeanWrapperImpl(object).getPropertyValue(id);
		Object postalCodeVal = new BeanWrapperImpl(object).getPropertyValue(postalCode);
		Object streetNumberVal = new BeanWrapperImpl(object).getPropertyValue(streetNumber);
		
		if (object instanceof EditLocationDTO) {
			/*in edit mode*/
			/*check if location has the same pc en streetnr as the location in the db*/
			/*if pc and streetnr are equal the location is valid--> return true*/
			/*if not perform the regular check*/
			Location locationDb = service.getLocation((Long)(idVal));
			String streetNumberDB = locationDb.getStreetNumber();
			String postalCodeDB = locationDb.getPostalCode();
			if(streetNumberDB.equals(streetNumberVal.toString()) && postalCodeDB.equals(postalCodeVal))
				return true;
		} 
		/*check if location with the pc and streetnr already exists in the db*/
		List<Location> locationByPostalCodeAndStreetNumber = service
				.getLocationByPostalCodeAndStreetNumber(postalCodeVal.toString(), streetNumberVal.toString());
		return locationByPostalCodeAndStreetNumber.isEmpty();
	}
}