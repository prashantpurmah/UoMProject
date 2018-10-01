package com.accenture.academy.esdb.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.accenture.academy.esdb.entities.Asset;

public interface AssetElasticsearchRepository extends ElasticsearchRepository<Asset, String>{

}
