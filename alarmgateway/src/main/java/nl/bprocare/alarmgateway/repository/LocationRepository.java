package nl.bprocare.alarmgateway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.bprocare.alarmgateway.dto.EditLocationDTO;
import nl.bprocare.alarmgateway.pojo.Location;

@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location, Long> {
	public List<Location> findByPostalCodeAndStreetNumber(String postalCode,String streetNumber);

}
