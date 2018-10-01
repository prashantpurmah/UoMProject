package com.accenture.academy.esdb.dtos;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author b.purmah
 * @see Used to make an entry to database
 *	when an asset is being assigned to
 *	an employee. 
 */
public class EmployeeAssetDto {
	
	@NotBlank
	private String employeeId;
	@NotBlank
	private String assetId;
	
	private Date time_taken;
	
	private Date time_returned;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public Date getTime_taken() {
		return time_taken;
	}
	public void setTime_taken(Date time_taken) {
		this.time_taken = time_taken;
	}
	public Date getTime_returned() {
		return time_returned;
	}
	public void setTime_returned(Date time_returned) {
		this.time_returned = time_returned;
	}
	
	

}
