package constraints;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import nl.bprocare.alarmgateway.domain.Location;
import nl.bprocare.alarmgateway.service.LocationService;

public class UniqueLocationValidator implements ConstraintValidator<UniqueLocation, Object> {

	private String postalCode;
	private String streetNumber;
	
	@Autowired
	private LocationService service;

	public UniqueLocationValidator() {
	}
	
	@Override
	public void initialize(UniqueLocation uniqueLocation) {
		this.postalCode = uniqueLocation.postalCode();
		this.streetNumber = uniqueLocation.streetNumber();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		Object postalCodeVal = new BeanWrapperImpl(object).getPropertyValue(postalCode);
		Object streetNumberVal = new BeanWrapperImpl(object).getPropertyValue(streetNumber);
		List<Location> locationByPostalCodeAndStreetNumber = service.getLocationByPostalCodeAndStreetNumber(postalCodeVal.toString(), streetNumberVal.toString());
		return locationByPostalCodeAndStreetNumber.isEmpty();
	}

}