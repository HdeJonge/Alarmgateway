package nl.bprocare.alarmgateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.bprocare.alarmgateway.dto.LocationDto;
import nl.bprocare.alarmgateway.pojo.Location;
import nl.bprocare.alarmgateway.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public List<LocationDto> getAllLocations() {
		return locationRepository.findAll();
	}

	public void saveLocation(LocationDto locationDto) {
		locationRepository.save(locationDto);
	}

	public void deleteLocation(Long locationId) {
		locationRepository.deleteById(locationId);
	}

	public void updateLocation(LocationDto location) {
		locationRepository.save(location);
	}

	public LocationDto getLocation(Long id) {
		return locationRepository.findById(id).get();
		
	}
	public List<LocationDto> getLocationByPostalCodeAndStreetNumber(String postalCode, String streetNumber){
		return locationRepository.findByPostalCodeAndStreetNumber(postalCode, streetNumber);
	}
}
