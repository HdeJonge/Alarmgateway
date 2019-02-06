package nl.bprocare.alarmgateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.bprocare.alarmgateway.domain.Location;
import nl.bprocare.alarmgateway.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	LocationRepository locationRepository;
	
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}

	public void saveLocation(Location location) {
		locationRepository.save(location);
		
	}

	public void deleteLocation(Long noteId) {
		locationRepository.deleteById(noteId);
		
	}

	public void updateLocation(Location location) {
		locationRepository.save(location);
	}

	public Location getLocation(Long id) {
		return locationRepository.findById(id).get();
		
	}
	

}
