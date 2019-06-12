package com.bprocare.repository;


import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.bprocare.domain.AlarmGateway;
import com.bprocare.domain.Location;


@Repository
public interface AlarmGatewayRepository extends DataTablesRepository<AlarmGateway, Long>{

	AlarmGateway findById(long id);
	
	List<AlarmGateway> findByLocation(Location location);
	
}
