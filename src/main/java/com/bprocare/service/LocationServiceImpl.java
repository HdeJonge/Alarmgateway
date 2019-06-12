package com.bprocare.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.bprocare.domain.Label;
import com.bprocare.domain.Location;
import com.bprocare.repository.AlarmGatewayRepository;
import com.bprocare.repository.LabelRepository;
import com.bprocare.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	AlarmGatewayRepository alarmGatewayRepository;
	
	@Autowired
	LabelRepository labelRepository;

	public List<Location> getAllLocations() {
		return (List<Location>) locationRepository.findAll();
	
	}	
	
	public Location getLocation(Long id) {
		return locationRepository.findById(id)
				.orElse(null);
	}

	@Transactional
	public Location createLocation(Location location) {
		return locationRepository.save(location);

	}

	@Transactional
	public Location changeLocation(Location location) {
		return locationRepository.save(location);

	}
	
	public boolean checkpostalcode(Location location)
	{
		
		if (locationRepository.findByPostalcodeAndHousenumber(location.getPostalcode(), location.getHousenumber()) == null)
            return true;
        else
        	return false;
	}

	@Transactional
	public void deleteLocation(Long id) throws Exception {
	
		Location location = locationRepository.findById(id)
	            .orElse(null);
		
    	if (location != null)
    	{

    		if (alarmGatewayRepository.findByLocation(location).size() == 0)
    		{
    		  locationRepository.delete(location);

    		}
    		else
    		{   
    			throw new Exception();
    		}
    	}
    	else 
    		throw new Exception();

	}
	
	public boolean comparePostalAndHousenum(Location location)
	{
		Location locationFromDb = locationRepository.findById(location.getId())
		                          .orElse(null);
		
		if ( locationFromDb.getPostalcode().equalsIgnoreCase(location.getPostalcode()) &&
		     locationFromDb.getHousenumber().equalsIgnoreCase(location.getHousenumber()) )
		{
			return true;
		}
		else
			return false;
		  
	}
	
	
	public DataTablesOutput<Location> getAllLocationsJSON(DataTablesInput input)
	{
		return locationRepository.findAll(input);
	}
	
	public List<Label> getAllLabels()
	{
		return (List<Label>) labelRepository.findAll();
	}

}
