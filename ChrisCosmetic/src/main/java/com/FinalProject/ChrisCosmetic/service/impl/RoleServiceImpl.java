package com.FinalProject.ChrisCosmetic.service.impl;

import com.FinalProject.ChrisCosmetic.entity.Role;
import com.FinalProject.ChrisCosmetic.repository.RoleRepository;
import com.FinalProject.ChrisCosmetic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
