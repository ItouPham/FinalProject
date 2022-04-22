package com.FinalProject.ChrisCosmetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.ChrisCosmetic.entity.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    List<Role> findAll();

    Role findByRoleName(String roleName);
}