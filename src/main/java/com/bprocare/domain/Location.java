package com.bprocare.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.bprocare.constraint.ValidPostalCode;


@Entity(name = "Location")
@Table(name = "location")
public class Location {
		
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
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
			
	  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })  
	  @JoinTable(name = "location_label", joinColumns = @JoinColumn(name =
	  "location_id"), inverseJoinColumns = @JoinColumn(name = "label_id") ) private
	  List<Label> labels = new ArrayList<>();
	 	
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
