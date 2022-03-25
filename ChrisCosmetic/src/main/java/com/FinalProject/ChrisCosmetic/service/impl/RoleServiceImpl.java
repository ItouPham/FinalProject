package com.FinalProject.ChrisCosmetic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalProject.ChrisCosmetic.repository.RoleRepository;
import com.FinalProject.ChrisCosmetic.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository repository;
}
