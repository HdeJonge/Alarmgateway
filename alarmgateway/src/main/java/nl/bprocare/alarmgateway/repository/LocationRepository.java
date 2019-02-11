package nl.bprocare.alarmgateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.bprocare.alarmgateway.domain.Location;

@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location, Long> {

}
