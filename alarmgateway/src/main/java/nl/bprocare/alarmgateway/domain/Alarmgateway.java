package nl.bprocare.alarmgateway.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="alarm_gateway")
public class Alarmgateway {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String mac;
	private SettingsProfile settingsProfile;
	
	@OneToOne
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
