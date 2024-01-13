/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.user.entity;

import com.omar.cms.business.page.entity.Page;
import com.omar.cms.business.page.entity.Role;
import com.omar.cms.business.page.entity.RoleAssignment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "USERS", schema = "CMS")
@NamedQueries({
  @NamedQuery(name = User.findAll, query = "SELECT u FROM User u"),
  @NamedQuery(name = User.findUserByEmail, query = "SELECT u FROM User u where u.email = :email")})
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  static final String PREFIX = "User.";
  public static final String findAll = PREFIX + "findAll";
  public static final String findUserByEmail = PREFIX + "findUserByEmail";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "FULLNAME")
  @NotBlank
  @NotEmpty
  private String fullName;

  @Column(name = "PASSWORD")
  @Size(min = 6)
  private String password;

  @Column(name = "MOBILENUMBER")
  @Size(min = 10, max = 10)
  private String mobileNumber;

  @Column(name = "EMAIL", unique = true)
  @Email
  private String email;

  /*
  @OneToMany
  @JoinTable(schema = "CMS",name = "page_role")
  @MapKeyJoinColumn(name = "page_id")
  private Map<Page, Role> pageRoles = new HashMap<>();*/
  @OneToMany(mappedBy = "user")
  Set<RoleAssignment> roleAssignments = new HashSet<>();

  public User() {
  }

  public User(String fullName, String password, String mobileNumber, String email) {
    this.fullName = fullName;
    this.password = password;
    this.mobileNumber = mobileNumber;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getfullName() {
    return fullName;
  }

  public void setfullName(String FullName) {
    this.fullName = FullName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Set<RoleAssignment> getRoleAssignments() {
    return roleAssignments;
  }

  public void setRoleAssignments(Set<RoleAssignment> roleAssignments) {
    this.roleAssignments = roleAssignments;
  }


  @Override
  public String toString() {
    return "User{" + "id=" + id + ", fullName=" + fullName + ", mobileNumber=" + mobileNumber + ", email=" + email + '}';
  }

}
