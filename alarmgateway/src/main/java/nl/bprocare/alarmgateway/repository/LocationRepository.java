package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.bprocare.alarmgateway.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
