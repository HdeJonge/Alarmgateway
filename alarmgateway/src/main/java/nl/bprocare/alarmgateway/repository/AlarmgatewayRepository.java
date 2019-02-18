package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.bprocare.alarmgateway.dto.AlarmgatewayDto;

public interface AlarmgatewayRepository extends JpaRepository<AlarmgatewayDto,Long>{

}
