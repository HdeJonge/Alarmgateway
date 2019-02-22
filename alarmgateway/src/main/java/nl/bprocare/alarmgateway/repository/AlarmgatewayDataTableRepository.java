package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.pojo.Alarmgateway;

public interface AlarmgatewayDataTableRepository extends DataTablesRepository<Alarmgateway, Long>{

}
