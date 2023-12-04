package com.example.project_spring.dto.facility;

public class CreateDTO {	

	private String facilityName;

	public CreateDTO() {
		// TODO Auto-generated constructor stub
	}

	public CreateDTO(String facilityName) {
		super();
		this.facilityName = facilityName;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
}
