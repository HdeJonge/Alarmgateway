package nl.bprocare.alarmgateway.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import nl.bprocare.alarmgateway.pojo.SettingsProfile;

public class CreateAlarmgatewayDTO {
	@NotNull
	@NotEmpty
	private String mac;
	@NotNull
	private SettingsProfile settingsProfile;

	private EditLocationDTO location;
	
	public CreateAlarmgatewayDTO() {
		
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
	
	public EditLocationDTO getLocation() {
		return location;
	}
	public void setLocation(EditLocationDTO location) {
		this.location = location;
	}
}
