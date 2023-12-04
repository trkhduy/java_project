package com.example.project_spring.dto.policy;

public class CreateDTO {
	private String policyName;

	public CreateDTO() {
		// TODO Auto-generated constructor stub
	}

	public CreateDTO(String policyName) {
		super();
		this.policyName = policyName;
	}

	public String getpolicyName() {
		return policyName;
	}

	public void setpolicyName(String policyName) {
		this.policyName = policyName;
	}
}
