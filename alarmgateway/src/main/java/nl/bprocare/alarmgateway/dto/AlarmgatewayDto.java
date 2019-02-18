package nl.bprocare.alarmgateway.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nl.bprocare.alarmgateway.pojo.SettingsProfile;

@Entity
@Table(name="alarm_gateway")
public class AlarmgatewayDto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String mac;
	private SettingsProfile settingsProfile;
	
	@OneToOne
	private LocationDto location;
	
	public AlarmgatewayDto() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public SettingsProfile getSettingsProfile() {
		return settingsProfile;
	}
	public void setSettingsProfile(SettingsProfile settingsProfile) {
		this.settingsProfile = settingsProfile;
	}
	
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
}
