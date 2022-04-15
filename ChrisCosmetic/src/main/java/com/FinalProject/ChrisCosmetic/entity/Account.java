package com.FinalProject.ChrisCosmetic.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.Set;

@DynamicUpdate
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column()
    private String email;

    @Column()
    private String password;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column
    private String address;

    @Column(length = 11)
    private String telephone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = {@JoinColumn(name="account_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Set<Role> roles;

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

    public Account() {
        super();
    }

}