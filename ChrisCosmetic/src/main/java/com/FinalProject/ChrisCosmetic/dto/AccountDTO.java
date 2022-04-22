package com.FinalProject.ChrisCosmetic.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.FinalProject.ChrisCosmetic.entity.Role;

import java.util.Set;

public class AccountDTO {

    private String id;

    @NotBlank(message = "Email can not empty")
    @Email(message = "Invalid email")
    private String email;

    private String password;

    private String confirmPassword;

    @NotBlank(message = "First name can not empty")
    private String firstName;

    @NotBlank(message = "Last name can not empty")
    private String lastName;

    private String address;

    private String telephone;

    private Set<Role> roles;

    private String roleID;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getConfirmPassword() {
	return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public AccountDTO(String id, String email, String password, String confirmPassword, String firstName, String lastName, String address, String telephone, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.roles = roles;
    }

    public AccountDTO() {
    }

    @Override
    public String toString() {
	return "CreateUpdateAccountDTO [id=" + id + ", email=" + email + ", password=" + password + ", confirmPassword="
		+ confirmPassword + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
		+ ", telephone=" + telephone + ", role=" + roles + "]";
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
