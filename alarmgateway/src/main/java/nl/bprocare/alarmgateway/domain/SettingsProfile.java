package nl.bprocare.alarmgateway.domain;

public enum SettingsProfile {
	A (1),
	B (2),
	C (3);
	
	private final int profileValue;
	
	private SettingsProfile(int profileValue) {
		this.profileValue = profileValue;
	}
	
}
