package com.example.project_spring.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Acc")
public class Acc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_id")
	private int accId; 
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;

	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@OneToMany(mappedBy = "acc", fetch = FetchType.EAGER)

	private Set<AccountRole> accRoles;
	
	@OneToMany(mappedBy = "acc")
	private List<Booking> bookings;
	
	public Acc() {
		// TODO Auto-generated constructor stub
	}

	public Acc(int accId, String username, String email, String avatar, String phoneNumber, String password,
			Boolean enabled, Set<AccountRole> accRoles) {
		super();
		this.accId = accId;
		this.username = username;
		this.email = email;
		this.avatar = avatar;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.enabled = enabled;
		this.accRoles = accRoles;
	}



	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<AccountRole> getAccRoles() {
		return accRoles;
	}

	public void setAccRoles(Set<AccountRole> accRoles) {
		this.accRoles = accRoles;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
