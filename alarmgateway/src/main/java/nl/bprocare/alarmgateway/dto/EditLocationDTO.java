package nl.bprocare.alarmgateway.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import constraints.UniqueLocation;

import java.util.List;

@UniqueLocation(
		id = "id",
		postalCode = "postalCode",
		streetNumber = "streetNumber"
		
		)
public class EditLocationDTO {
	private Long id;
	@NotNull
	@NotEmpty
	private String street;
	@NotNull
	@NotEmpty
	private String streetNumber;
	@NotNull
	@NotEmpty
	private String town;
	@NotNull
	@NotEmpty
	private String postalCode;
	@NotNull
	@NotEmpty
	private String phoneNumber;

	private List<LabelDTO> labelList;

	public List<LabelDTO> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<LabelDTO> labels) {
		this.labelList = labels;
	}

	public void addLabel(LabelDTO label) {
		this.labelList.add(label);
	}
	public void removeLabel(LabelDTO label) {
		this.labelList.remove(label);
	}
	public EditLocationDTO() {
		
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

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocationDescription() {
		return 
				street + " " 
				+ streetNumber +  " "
				+ town + " "
				+ postalCode + " "
				+ phoneNumber;
	}
}
