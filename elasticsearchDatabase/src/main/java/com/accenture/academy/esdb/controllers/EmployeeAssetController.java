package com.accenture.academy.esdb.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academy.esdb.dtos.EmployeeAssetDto;
import com.accenture.academy.esdb.services.EmployeeAssetService;

@RestController
@RequestMapping("/employee-asset")
public class EmployeeAssetController {

	private EmployeeAssetService employeeAssetService;
	
	public EmployeeAssetController(EmployeeAssetService employeeAssetService) {
		this.employeeAssetService = employeeAssetService;
	}
	
	@PostMapping
	public ResponseEntity<String> assignAsset(@RequestBody EmployeeAssetDto employeeAssetDto){
		
		String response = employeeAssetService.assignAssetToEmployee(
					employeeAssetDto.getEmployeeId(),
					employeeAssetDto.getAssetId()
					);
		
		if (!response.equals("NotFound") && !response.equals("Found")) {
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} else if(response.equals("NotFound")) {
			//asset not found
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			//Asset already assigned
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/{assetId}")
	public ResponseEntity<String> returnAsset(@PathVariable String assetId){
		String response = employeeAssetService.returnAsset(assetId);
		if (!response.equals("NotFound") && !response.equals("Error")) {
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} else if(response.equals("NotFound")) {
			//Asset not found/doesn't exist
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			//Asset not assigned to anyone
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public List<EmployeeAssetDto> findAll(){
		return employeeAssetService.findAll();
	}
	
}
