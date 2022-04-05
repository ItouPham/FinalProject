package com.FinalProject.ChrisCosmetic.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.FinalProject.ChrisCosmetic.entity.Role;

public class AccountDTO {

    private Long id;

    @NotBlank(message = "Email can not empty")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password can not empty")
    @Length(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm password can not empty")
    @Length(min = 6, message = "Confirm password must be at least 6 characters")
    private String confirmPassword;

    @NotBlank(message = "First name can not empty")
    private String firstName;

    @NotBlank(message = "Last name can not empty")
    private String lastName;

    private String address;

    private String telephone;

    private Role role;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
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

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public AccountDTO(Long id, String email, String password, String confirmPassword, String firstName,
                      String lastName, String address, String telephone, Role role) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
	this.confirmPassword = confirmPassword;
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.telephone = telephone;
	this.role = role;
    }

    public AccountDTO() {
    }

    @Override
    public String toString() {
	return "CreateUpdateAccountDTO [id=" + id + ", email=" + email + ", password=" + password + ", confirmPassword="
		+ confirmPassword + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
		+ ", telephone=" + telephone + ", role=" + role + "]";
    }

}
