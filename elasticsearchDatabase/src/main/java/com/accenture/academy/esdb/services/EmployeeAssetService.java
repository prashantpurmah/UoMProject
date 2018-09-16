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
		if(assetRequested != null) {
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
		return "NotFound";
	}
	
	public List<EmployeeAssetDto> findAll(){
		List<EmployeeAsset> employeeAssets = employeeAssetRepository.findAll();
		List<EmployeeAssetDto> employeeAssetDtos = new ArrayList<>();
		for (EmployeeAsset employeeAsset : employeeAssets) {
			EmployeeAssetDto employeeAssetDto = new EmployeeAssetDto();
			employeeAssetDto.setEmployeeId(employeeAsset.getEmployee().getName());
			employeeAssetDto.setAssetId(employeeAsset.getAsset().getAssetId());
			employeeAssetDto.setTime_taken(employeeAsset.getDateTaken());
			employeeAssetDto.setTime_returned(employeeAsset.getDateReturned());
			
			employeeAssetDtos.add(employeeAssetDto);
		}
		
		return employeeAssetDtos;
	}
}
