package com.FinalProject.ChrisCosmetic.service;

import com.FinalProject.ChrisCosmetic.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole();

    Role findRoleByRoleName(String roleName);
}
