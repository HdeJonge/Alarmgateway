package com.bprocare.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.bprocare.domain.Label;
import com.bprocare.domain.Location;

public interface LocationService {

	List<Location> getAllLocations();
	
	DataTablesOutput<Location> getAllLocationsJSON(DataTablesInput input);
	
	Location getLocation(Long id);

	Location createLocation(Location location);

	Location changeLocation(Location location);
	
	boolean checkpostalcode(Location location);

	void deleteLocation(Long id) throws Exception;
	
	boolean comparePostalAndHousenum(Location location);

	public List<Label> getAllLabels();
}
