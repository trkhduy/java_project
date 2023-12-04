package com.example.project_spring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Rooms")
public class Room {
	
	@Id
	@Column(name = "room_id")
	private String roomId;
	
	@Column(name = "bed_type")
	private String bedType;
	
	@Column(name = "room_name")
	private String roomName;
	
	@Column(name = "price_per_night")
	private Float pricePerNight;
	
	@Column(name = "sale_price")
	private Float salePrice;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "room_size")
	private Float roomSize;
	
	@Column(name = "availability")
	private String availability;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id",referencedColumnName="hotel_id")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "room_type_id",referencedColumnName = "room_type_id")
	private RoomType roomType;
	
	@OneToMany(mappedBy = "room")
	private List<RoomFacility> roomFacilities;
	
	@OneToMany(mappedBy = "room")
	private List<RoomPolicy> roomPolicies;
	
	@OneToMany(mappedBy = "room")
	private List<Image> images;
	
	@OneToMany(mappedBy = "room")
	private List<BookingDetail> bookingDetails;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}
	
	public Room(String roomId, String bedType, String roomName, Float pricePerNight, Float salePrice, String image,
			Float roomSize, String availability, Hotel hotel, RoomType roomType, List<RoomFacility> roomFacilities,
			List<RoomPolicy> roomPolicies, List<Image> images) {
		super();
		this.roomId = roomId;
		this.bedType = bedType;
		this.roomName = roomName;
		this.pricePerNight = pricePerNight;
		this.salePrice = salePrice;
		this.image = image;
		this.roomSize = roomSize;
		this.availability = availability;
		this.hotel = hotel;
		this.roomType = roomType;
		this.roomFacilities = roomFacilities;
		this.roomPolicies = roomPolicies;
		this.images = images;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public Float getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(Float pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Float getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Float roomSize) {
		this.roomSize = roomSize;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public List<RoomFacility> getRoomFacilities() {
		return roomFacilities;
	}

	public void setRoomFacilities(List<RoomFacility> roomFacilities) {
		this.roomFacilities = roomFacilities;
	}

	public List<RoomPolicy> getRoomPolicies() {
		return roomPolicies;
	}

	public void setRoomPolicies(List<RoomPolicy> roomPolicies) {
		this.roomPolicies = roomPolicies;
	}

	public Float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
}
