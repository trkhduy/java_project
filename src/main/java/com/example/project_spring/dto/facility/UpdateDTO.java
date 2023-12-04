package com.example.project_spring.dto.facility;

public class UpdateDTO {
	private int facilityId;
	private String facilityName;

	public UpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public UpdateDTO(int facilityId, String facilityName) {
		super();
		this.facilityId = facilityId;
		this.facilityName = facilityName;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

}
