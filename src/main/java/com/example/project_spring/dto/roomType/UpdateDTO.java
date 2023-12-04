package com.example.project_spring.dto.roomType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateDTO {
	private Integer roomTypeId;
	
	@NotEmpty(message = "Không được để trống tên")
	private String typeName;

	private String description;

	@NotNull(message = "Vui lòng nhập số lượng")
	@Min(value = 1, message = "Số lượng phải lớn hơn 0")
	private Integer maxOccupancy;
	
	public UpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public UpdateDTO(Integer roomTypeId, @NotEmpty(message = "Không được để trống tên") String typeName,
			String description,
			@NotNull(message = "Vui lòng nhập số lượng") @Min(value = 1, message = "Số lượng phải lớn hơn 0") Integer maxOccupancy) {
		super();
		this.roomTypeId = roomTypeId;
		this.typeName = typeName;
		this.description = description;
		this.maxOccupancy = maxOccupancy;
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
}
