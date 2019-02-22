package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import nl.bprocare.alarmgateway.pojo.Label;

public interface LabelRestRepository extends DataTablesRepository<Label, Long>{

}
