/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.page.entity;


import com.omar.cms.business.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "Roles")
public class Role implements Serializable {

  @Id
  @GeneratedValue
  private long id;

 @OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
  @JoinColumn(name = "PAGE_ID")
  private Page page;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  private User user;

  @Enumerated(EnumType.STRING)
  private RoleEnums RoleEnum;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
    hash = 97 * hash + Objects.hashCode(this.page);
    hash = 97 * hash + Objects.hashCode(this.user);
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
    if (!Objects.equals(this.page, other.page)) {
      return false;
    }
    if (!Objects.equals(this.user, other.user)) {
      return false;
    }
    return this.RoleEnum == other.RoleEnum;
  }

  @Override
  public String toString() {
    return "Role{" + "id=" + id + ", page=" + page + ", user=" + user + ", enums=" + RoleEnum + '}';
  }
  
  

}
