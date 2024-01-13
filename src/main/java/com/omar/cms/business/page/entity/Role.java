/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "user_roles", schema = "CMS")
public class Role implements Serializable {

  @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Enumerated(EnumType.STRING)
  private RoleEnums RoleEnum;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public RoleEnums getRoleEnum() {
    return RoleEnum;
  }

  public void setRoleEnum(RoleEnums RoleEnum) {
    this.RoleEnum = RoleEnum;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 97 * hash + Objects.hashCode(this.RoleEnum);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Role other = (Role) obj;
    if (this.id != other.id) {
      return false;
    }

    return this.RoleEnum == other.RoleEnum;
  }

  @Override
  public String toString() {
    return "Role{" + "id=" + id + ", RoleEnum=" + RoleEnum + '}';
  }

}
