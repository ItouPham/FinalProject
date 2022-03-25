package com.FinalProject.ChrisCosmetic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.ChrisCosmetic.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	Optional<SubCategory> findBySubCategoryName(String subCategoryName);
}