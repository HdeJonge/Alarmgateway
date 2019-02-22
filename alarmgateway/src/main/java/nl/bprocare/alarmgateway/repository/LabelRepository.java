package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.bprocare.alarmgateway.pojo.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

}
