package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.domain.Label;

public interface LabelRestRepository extends DataTablesRepository<Label, Long>{

}
