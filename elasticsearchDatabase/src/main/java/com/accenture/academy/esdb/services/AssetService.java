package com.accenture.academy.esdb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academy.esdb.entities.Asset;
import com.accenture.academy.esdb.repositories.AssetElasticsearchRepository;
import com.accenture.academy.esdb.repositoriesDb.AssetRepository;

@Service
public class AssetService {

	private AssetRepository assetRepository;
	private AssetElasticsearchRepository assetElasticsearchRepository;
	
	@Autowired
	public AssetService(AssetRepository assetRepository, AssetElasticsearchRepository assetElasticsearchRepository) {
		this.assetRepository = assetRepository;
		this.assetElasticsearchRepository = assetElasticsearchRepository;
	}
	
	public Asset findById(String id) {
		return assetRepository.findOne(id);
	}
}
