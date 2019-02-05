package nl.bprocare.alarmgateway.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
