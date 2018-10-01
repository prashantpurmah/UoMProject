package com.accenture.academy.esdb.dtos;

import javax.validation.constraints.NotNull;

/**
 * @author b.purmah
 * Used during user logging to the system
 */
public class UserDto {
	
	@NotNull
	private String employeeId;
	
	@NotNull
	private String password;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDto [employeeId=" + employeeId + ", password=" + password + "]";
	}
	
	
}
