package com.bprocare.web.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.bprocare.constraint.ValidPostalCode;
import com.bprocare.domain.Label;

@ValidPostalCode(message = "{postalcode.exists}", isUpdate = false)
public class LocationDto {

	private Long id;

	@NotNull(message = "{street.null}")
	@NotEmpty(message = "{street.null}")
	private String street;
	@NotNull(message = "{place.null}")
	@NotEmpty(message = "{place.null}")
	private String place;
	@NotNull(message = "{housenumber.null}")
	@NotEmpty(message = "{housenumber.null}")
	private String housenumber;

	private String postalcode;
	private String telephone;


	private List<Label> labels = new ArrayList<>();
	
	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
