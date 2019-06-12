package com.bprocare.web.dto;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.bprocare.domain.Location;
import com.bprocare.domain.SettingsProfile;

public class AlarmGatewayDtoChange {

    private Long id;
	
    @NotEmpty
    @NotNull( message = "{MAC.null}" )
    @Pattern(regexp="^([0-9A-Fa-f]{2}[\\.:-]){5}([0-9A-Fa-f]{2})$",
    message="{MAC.error}")
	private String mac;

    @NotEmpty
	@NotNull( message = "{settingsprofile.null}" )
	@Enumerated(EnumType.STRING)
	private SettingsProfile settingsprofile;	
	
    @NotEmpty
	@NotNull( message = "{location.null}" )
	private Location location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String Mac) {
		mac = Mac;
	}

	public SettingsProfile getSettingsprofile() {
		return settingsprofile;
	}

	public void setSettingsprofile(SettingsProfile settingsprofile) {
		this.settingsprofile = settingsprofile;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
