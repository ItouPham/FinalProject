package com.FinalProject.ChrisCosmetic.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	private String id;

	@Column
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private Set<Account> accounts;

	public Role(String id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Role() {
		super();
	}

}