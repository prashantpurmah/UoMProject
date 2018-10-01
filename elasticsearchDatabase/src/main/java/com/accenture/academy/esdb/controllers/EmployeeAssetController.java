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
import com.accenture.academy.esdb.entities.EmployeeAsset;
import com.accenture.academy.esdb.services.EmployeeAssetService;

@RestController
@RequestMapping("/employee-asset")
public class EmployeeAssetController {

	private EmployeeAssetService employeeAssetService;
	
	public EmployeeAssetController(EmployeeAssetService employeeAssetService) {
		this.employeeAssetService = employeeAssetService;
	}
	
	/**
	 * 
	 * Method to assign Asset to an employee
	 * @param employeeAssetDto
	 * @return 202 if successful
	 * 		   404 if asset not found
	 * 		   409 if asset already assigned
	 */
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
	
	/**
	 * @param assetId
	 * @return
	 * 		404 if asset ID is invalid
	 * 		202 if successful
	 * 		400 if asset not assigned
	 */
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
	
	/**
	 * @return List of EmployeeAssetDto
	 */
	@GetMapping
	public List<EmployeeAsset> findAll(){
		return employeeAssetService.findAll();
	}
	
	@GetMapping("/{assetId}")
	public ResponseEntity<String> processAsset(@PathVariable String assetId){
		String response = employeeAssetService.processAsset(assetId);
		if(response.equals("NotFound")) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		else if(response.equals("return")) {
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		}
		else if(response.equals("assign")) {
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	} 
	
}
