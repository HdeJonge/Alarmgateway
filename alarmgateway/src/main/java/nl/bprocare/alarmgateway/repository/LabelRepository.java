package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.bprocare.alarmgateway.dto.LabelDto;

public interface LabelRepository extends JpaRepository<LabelDto, Long>{

}
