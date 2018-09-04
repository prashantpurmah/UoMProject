package com.accenture.academy.esdb.dtos;

public class EmployeeScore {
	
	private String employeeId;
	private String employeeName;
	private Float score;
	
	public EmployeeScore() {}
	public EmployeeScore(String employeeId, String employeeName, Float score) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.score = score;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	
	

}
