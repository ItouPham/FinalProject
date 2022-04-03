package com.FinalProject.ChrisCosmetic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @NotBlank(message = "Email can not empty")
    @Email(message = "Invalid email")
    @Column()
    private String email;

    @NotBlank(message = "Password can not empty")
    @Length(min = 6, message = "Password must be at least 6 characters")
    @Column()
    private String password;

    @NotBlank(message = "Confirm password can not empty")
    @Length(min = 6, message = "Confirm password must be at least 6 characters")
    @Column()
    private String confirmPassword;

    @NotBlank(message = "First name can not empty")
    @Column()
    private String firstName;

    @NotBlank(message = "Last name can not empty")
    @Column()
    private String lastName;

    @Column
    private String address;

    @Column(length = 11)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "role_id")
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

    public Account() {
	super();
    }

}
