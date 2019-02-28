package nl.bprocare.alarmgateway.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

public class LabelDTO {

	@NotEmpty
	private String description;
	
	public LabelDTO() {
		
	}

	public LabelDTO(String description) {
		super();
		this.description = description;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    @Override
	public int hashCode() {
        int result = 17;
        result = 31 * result + description.hashCode();
		return result;
	}
/*
    @Override
    public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
        if (o == this) return true;
        if (!(o instanceof LabelDTO)) {
            return false;
        }

        LabelDTO label = (LabelDTO) o;

        return label.description.equals(description) &&
                label.id.equals(id);
    }
	*/
    @Override
    public String toString() {
    	return this.description;
    }


}
