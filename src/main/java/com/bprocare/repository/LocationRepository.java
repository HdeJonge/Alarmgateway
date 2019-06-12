package com.bprocare.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.bprocare.domain.Location;

@Repository
public interface LocationRepository extends DataTablesRepository<Location, Long> {

	Location findById(long id);
	
	Location findByPostalcodeAndHousenumber(String postalcode,final String housenumber);
		
}
