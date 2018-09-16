package com.accenture.academy.esdb.repositoriesDb;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academy.esdb.entities.Asset;

public interface AssetRepository extends JpaRepository<Asset, String>{

}
