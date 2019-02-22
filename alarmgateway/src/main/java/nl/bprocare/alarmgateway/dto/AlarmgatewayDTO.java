package nl.bprocare.alarmgateway.dto;

import javax.validation.constraints.NotEmpty;

import nl.bprocare.alarmgateway.pojo.SettingsProfile;

public class AlarmgatewayDTO {

	private Long id;
	@NotEmpty
	private String mac;
	private SettingsProfile settingsProfile;

	private LocationDTO location;
	
	public AlarmgatewayDTO() {
		
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
	
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
}
