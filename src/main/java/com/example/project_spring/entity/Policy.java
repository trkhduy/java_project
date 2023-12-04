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
@Table(name = "policies")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "policy_id")
	private int policyId;

	@Column(name = "policy_name")
	private String policyName;
	
	@OneToMany(mappedBy = "policy")
	private List<RoomPolicy> roomPolicies;
	
	public Policy() {
		// TODO Auto-generated constructor stub
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public List<RoomPolicy> getRoomPolicies() {
		return roomPolicies;
	}

	public void setRoomPolicies(List<RoomPolicy> roomPolicies) {
		this.roomPolicies = roomPolicies;
	}
	
}
