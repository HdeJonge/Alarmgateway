package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.dto.LabelDto;

public interface LabelRestRepository extends DataTablesRepository<LabelDto, Long>{

}
