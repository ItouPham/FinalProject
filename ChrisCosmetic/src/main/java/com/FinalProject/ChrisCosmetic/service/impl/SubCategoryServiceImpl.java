package com.FinalProject.ChrisCosmetic.service.impl;

import com.FinalProject.ChrisCosmetic.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;

import com.FinalProject.ChrisCosmetic.repository.SubCategoryRepository;
import com.FinalProject.ChrisCosmetic.service.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public List<SubCategory> findAllSubCategory() {
		return subCategoryRepository.findAll();
	}
}
