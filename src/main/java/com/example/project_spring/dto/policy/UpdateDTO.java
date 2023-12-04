package com.example.project_spring.dto.policy;

public class UpdateDTO {
	private int policyId;
	private String policyName;

	public UpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public UpdateDTO(int policyId, String policyName) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
	}

	public int getpolicyId() {
		return policyId;
	}

	public void setpolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getpolicyName() {
		return policyName;
	}

	public void setpolicyName(String policyName) {
		this.policyName = policyName;
	}

}
