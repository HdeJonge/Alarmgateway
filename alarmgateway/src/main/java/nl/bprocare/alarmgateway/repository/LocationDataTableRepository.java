package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.dto.LocationDto;

public interface LocationDataTableRepository extends DataTablesRepository<LocationDto, Long>{

}
