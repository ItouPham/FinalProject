package com.FinalProject.ChrisCosmetic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalProject.ChrisCosmetic.repository.CategoryRepository;
import com.FinalProject.ChrisCosmetic.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
}
