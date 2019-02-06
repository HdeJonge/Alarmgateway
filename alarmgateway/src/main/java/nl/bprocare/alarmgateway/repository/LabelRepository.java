package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nl.bprocare.alarmgateway.domain.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

}
