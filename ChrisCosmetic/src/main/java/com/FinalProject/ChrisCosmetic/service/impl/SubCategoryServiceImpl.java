package com.FinalProject.ChrisCosmetic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.FinalProject.ChrisCosmetic.repository.SubCategoryRepository;
import com.FinalProject.ChrisCosmetic.service.SubCategoryService;

public class SubCategoryServiceImpl implements SubCategoryService{
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
}
