package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.pojo.Location;

public interface LocationDataTableRepository extends DataTablesRepository<Location, Long>{

}
