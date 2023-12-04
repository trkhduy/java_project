package com.example.project_spring.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Bookings")
public class Booking {

	@Id
	@Column(name = "booking_id")
	private Integer bookingId;

	@Column(name = "address")
	private String address;

	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "paymentMethod")
	private String paymentMethod;
	
	@Column(name = "orderNote")
	private String orderNote;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "status")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "acc_id", referencedColumnName = "acc_id")
	private Acc acc;
	
	@OneToMany(mappedBy = "booking")
	private List<BookingDetail> bookingDetails;

	public Booking() {
		// TODO Auto-generated constructor stub
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Acc getAcc() {
		return acc;
	}

	public void setAcc(Acc acc) {
		this.acc = acc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<BookingDetail> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetail> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}
