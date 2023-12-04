package com.example.project_spring.dto.room;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.project_spring.entity.Hotel;
import com.example.project_spring.entity.RoomType;

public class UpdateDTO {

	@NotEmpty(message = "Vui lòng nhập id !")
	private String roomId;
	@NotEmpty(message = "Vui lòng nhập loại giường !")
	private String bedType;
	@NotEmpty(message = "Vui lòng tên phòng !")
	private String roomName;
	@NotNull(message = "Vui lòng nhập giá tiền !")
	@Positive(message = "Giá tiền phải là số dương !")
	private Float pricePerNight;
	@Positive(message = "Giá tiền phải là số dương !")
	private Float salePrice;

	private String image;
	@NotNull(message = "Vui lòng nhập size !")
	private Float roomSize;

	private String availability;

	private Hotel hotel;
	private RoomType roomType;

	public UpdateDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UpdateDTO(@NotEmpty(message = "Vui lòng nhập id !") String roomId,
			@NotEmpty(message = "Vui lòng nhập loại giường !") String bedType,
			@NotEmpty(message = "Vui lòng tên phòng !") String roomName,
			@NotNull(message = "Vui lòng nhập giá tiền !") @Positive(message = "Giá tiền phải là số dương !") Float pricePerNight,
			@Positive(message = "Giá tiền phải là số dương !") Float salePrice, String image,
			@NotNull(message = "Vui lòng nhập size !") Float roomSize, String availability, Hotel hotel,
			RoomType roomType) {
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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Float getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(Float pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public Float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
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

}
