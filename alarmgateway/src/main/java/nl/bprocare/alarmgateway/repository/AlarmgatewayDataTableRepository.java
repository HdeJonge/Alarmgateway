package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.domain.Alarmgateway;

public interface AlarmgatewayDataTableRepository extends DataTablesRepository<Alarmgateway, Long>{

}
