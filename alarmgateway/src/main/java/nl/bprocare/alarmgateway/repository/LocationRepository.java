package nl.bprocare.alarmgateway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.bprocare.alarmgateway.dto.LocationDto;
import nl.bprocare.alarmgateway.pojo.Location;

@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<LocationDto, Long> {
	public List<LocationDto> findByPostalCodeAndStreetNumber(String postalCode,String streetNumber);

}
