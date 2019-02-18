package nl.bprocare.alarmgateway.pojo;

import javax.validation.constraints.NotEmpty;

public class Alarmgateway {

	private Long id;
	@NotEmpty
	private String mac;
	@NotEmpty
	private SettingsProfile settingsProfile;

	private Location location;
	
	public Alarmgateway() {
		
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
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
