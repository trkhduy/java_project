package com.example.project_spring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "facilities")
public class Facility {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "facility_id")
	private int facilityId;

	@Column(name = "facility_name")
	private String facilityName;
	
	@OneToMany(mappedBy = "facility")
	private List<RoomFacility> roomFacilities;
	
	public Facility() {
		// TODO Auto-generated constructor stub
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

	public List<RoomFacility> getRoomFacilities() {
		return roomFacilities;
	}

	public void setRoomFacilities(List<RoomFacility> roomFacilities) {
		this.roomFacilities = roomFacilities;
	}

}
