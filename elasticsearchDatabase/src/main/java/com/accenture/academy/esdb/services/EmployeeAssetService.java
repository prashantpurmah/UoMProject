package com.accenture.academy.esdb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.academy.esdb.dtos.EmployeeAssetDto;
import com.accenture.academy.esdb.entities.Asset;
import com.accenture.academy.esdb.entities.Employee;
import com.accenture.academy.esdb.entities.EmployeeAsset;
import com.accenture.academy.esdb.repositoriesDb.AssetRepository;
import com.accenture.academy.esdb.repositoriesDb.EmployeeAssetRepository;
import com.accenture.academy.esdb.repositoriesDb.EmployeeDBRepository;

@Service
public class EmployeeAssetService {

	private EmployeeDBRepository employeeDBRepository;
	private AssetRepository assetRepository;
	private EmployeeAssetRepository employeeAssetRepository;
	
	public EmployeeAssetService(EmployeeDBRepository employeeDBRepository,
			AssetRepository assetRepository,
			EmployeeAssetRepository employeeAssetRepository) {
		this.assetRepository = assetRepository;
		this.employeeDBRepository = employeeDBRepository;
		this.employeeAssetRepository = employeeAssetRepository;
	}
	
	/**
	 * @param employeeId
	 * @param assetId
	 * @return String
	 * 		Found if asset already assigned to someone
	 * 		NotFound if assetId invalid
	 */
	public String assignAssetToEmployee(String employeeId,  String assetId) {
		Asset assetRequested = assetRepository.findOne(assetId);
		
		if (assetRequested != null) {
		
			List<EmployeeAsset> employeeAssets = employeeAssetRepository.findByAsset(assetRequested);
			for (EmployeeAsset employeeAsset : employeeAssets) {
				if(employeeAsset.getDateReturned() == null) {
					return "Found";
				}
			}
			Employee employee = employeeDBRepository.findOne(employeeId);
			
			EmployeeAsset employeeAsset = new EmployeeAsset();
			employeeAsset.setAsset(assetRequested);
			employeeAsset.setEmployee(employee);
			employeeAsset.setDateTaken(new Date());
			employeeAssetRepository.save(employeeAsset);
			return assetId + " assigned to " + employee.getName(); 
		
		}
		return "NotFound";
		
	}
	
	public String returnAsset(String assetId) {
			Asset assetRequested = assetRepository.findOne(assetId);
			List<EmployeeAsset> employeeAssets = employeeAssetRepository.findByAsset(assetRequested);
			for (EmployeeAsset employeeAsset : employeeAssets) {
				if(employeeAsset.getDateReturned() == null) {
					employeeAsset.setDateReturned(new Date());
					employeeAssetRepository.save(employeeAsset);
					return employeeAsset.getEmployee().getName();
				}
			}
			return "Error";
		
	}
	
	public List<EmployeeAsset> findAll(){
		List<EmployeeAsset> employeeAssets = employeeAssetRepository.findAll();
		
		return employeeAssets;
	}
	
	/**
	 * Check if asset needs to be returned or assigned to someone
	 * @param assetId
	 * @return 
	 */
	public String processAsset(String assetId) {
		Asset assetRequested = assetRepository.findOne(assetId);
		if(assetRequested != null) {	
			List<EmployeeAsset> employeeAssets = employeeAssetRepository.findByAsset(assetRequested);
			for (EmployeeAsset employeeAsset : employeeAssets) {
				if(employeeAsset.getDateReturned() == null) {
					return "returning";
				}
			}
			return "assign";	
		}
		else {
			return "NotFound";
		}
	} 
}
