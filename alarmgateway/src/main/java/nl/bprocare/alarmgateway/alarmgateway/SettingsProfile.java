package nl.bprocare.alarmgateway.alarmgateway;

public enum SettingsProfile {
	A (1),
	B (2),
	C (3);
	
	private final int profileValue;
	
	private SettingsProfile(int profileValue) {
		this.profileValue = profileValue;
	}
	
}
