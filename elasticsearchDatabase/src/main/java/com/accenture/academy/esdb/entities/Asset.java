package com.accenture.academy.esdb.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name="assets")
@Document(indexName = "employeedata", type = "assets")
public class Asset implements Serializable{
	
	@Id
	@org.springframework.data.annotation.Id
	private String assetId;
	
	private String type;

	@OneToMany(mappedBy = "asset")
	private Set<EmployeeAsset> employeeAssets;

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<EmployeeAsset> getEmployeeAssets() {
		return employeeAssets;
	}

	public void setEmployeeAssets(Set<EmployeeAsset> employeeAssets) {
		this.employeeAssets = employeeAssets;
	}

	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", type=" + type + "]";
	}
	
	

}
