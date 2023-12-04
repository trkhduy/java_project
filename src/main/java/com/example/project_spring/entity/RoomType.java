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
@Table(name = "Room_Types")
public class RoomType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_type_id")
	private Integer roomTypeId;

	@Column(name = "type_name")
	private String typeName;

	@Column(name = "description")
	private String description;

	@Column(name = "max_occupancy")
	private Integer maxOccupancy;

	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms;
	
	public RoomType() {
		// TODO Auto-generated constructor stub
	}

	public RoomType(Integer roomTypeId, String typeName, String description, Integer maxOccupancy, List<Room> rooms) {
		super();
		this.roomTypeId = roomTypeId;
		this.typeName = typeName;
		this.description = description;
		this.maxOccupancy = maxOccupancy;
		this.rooms = rooms;
	}



	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
}
