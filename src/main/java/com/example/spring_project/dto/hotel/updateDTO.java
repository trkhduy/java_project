package com.example.spring_project.dto.hotel;

import javax.validation.constraints.NotEmpty;

public class updateDTO {
	
	private Integer hotelId;
	
	@NotEmpty(message = "Không được để trống tên")
	private String name;
	@NotEmpty(message = "Không được để trống địa chỉ")
	private String address;
	private String image;
	private Float rating;
	
	public updateDTO() {
		// TODO Auto-generated constructor stub
	}

	public updateDTO(Integer hotelId, String name, String address, String image, Float rating) {
		super();
		this.hotelId = hotelId;
		this.name = name;
		this.address = address;
		this.image = image;
		this.rating = rating;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
}
