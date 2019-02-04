package nl.bprocare.alarmgateway.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class LocationService {
	
	@Autowired
	LocationRepository locationRepository;
	
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}
	

}
