package com.accenture.academy.esdb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academy.esdb.entities.Asset;
import com.accenture.academy.esdb.services.AssetService;

@RestController
@RequestMapping("/assets")
public class AssetController {

	private AssetService assetService;
	
	public AssetController(AssetService assetService) {
		this.assetService = assetService;
	}
	
	@GetMapping("/{id}/")
	public ResponseEntity<?> findById(@PathVariable String id) {
		Asset asset = assetService.findById(id);
		if (asset != null) {
			return new ResponseEntity<Asset>(asset, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
