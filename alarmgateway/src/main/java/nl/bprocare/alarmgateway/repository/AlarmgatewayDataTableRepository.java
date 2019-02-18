package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.dto.AlarmgatewayDto;

public interface AlarmgatewayDataTableRepository extends DataTablesRepository<AlarmgatewayDto, Long>{

}
