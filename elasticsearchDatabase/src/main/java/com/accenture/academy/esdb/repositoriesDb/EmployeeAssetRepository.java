package com.accenture.academy.esdb.repositoriesDb;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academy.esdb.entities.Asset;
import com.accenture.academy.esdb.entities.EmployeeAsset;

public interface EmployeeAssetRepository extends JpaRepository<EmployeeAsset, Integer>{

	public List<EmployeeAsset> findByAsset(Asset asset);
}
