package com.accenture.academy.esdb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author b.purmah
 * Database model for table where info about assets borrowed by
 * employee are recorded. 
 */
@Entity
@Table(name="employee_asset")
public class EmployeeAsset implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	@Column(name = "time_taken")
	private Date dateTaken;
	
	@Column(name = "time_returned")
	private Date dateReturned;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Date getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}
	
	
	public Date getDateReturned() {
		return dateReturned;
	}
	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	
	

}
