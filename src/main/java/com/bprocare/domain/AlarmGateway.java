package com.bprocare.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "AlarmGateway")
@Table(name = "alarmGateway")
public class AlarmGateway {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotEmpty
    @NotNull( message = "{MAC.null}" )
	private String mac;

	@NotNull( message = "{settingsprofile.null}" )
	@Enumerated(EnumType.STRING)
	private SettingsProfile settingsprofile;	
	
	@NotNull( message = "{location.null}" )
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH  })
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
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
