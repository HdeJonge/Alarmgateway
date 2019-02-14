package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.domain.Location;

public interface LocationDataTableRepository extends DataTablesRepository<Location, Long>{

}
