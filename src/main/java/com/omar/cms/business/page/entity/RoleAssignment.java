/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.cms.business.page.entity;

import com.omar.cms.business.user.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author carbo
 */
@Entity
@Table(schema = "CMS",name = "role_assignment")
public class RoleAssignment {

  @EmbeddedId
  private RoleAssignmentId roleAssignmentId; 
  
  @ManyToOne
  @MapsId(value = "pageid")
  private Page page;
  
  @ManyToOne
  @MapsId(value = "userId")
  private User user;
  //Here
  @ManyToOne
  @MapsId(value = "roleId")
  private Role role; 
  
  
  @Embeddable
  private static class RoleAssignmentId implements Serializable{
    private Long pageid;
    private Long roleId;
    private Long userId;
    
    public RoleAssignmentId() {
    }

    public RoleAssignmentId(Long pageid, Long roleId, Long userId) {
      this.pageid = pageid;
      this.roleId = roleId;
      this.userId = userId;
    }

    public Long getPageid() {
      return pageid;
    }

    public void setPageid(Long pageid) {
      this.pageid = pageid;
    }

    public Long getRoleId() {
      return roleId;
    }

    public void setRoleId(Long roleId) {
      this.roleId = roleId;
    }

    public Long getUserId() {
      return userId;
    }

    public void setUserId(Long userId) {
      this.userId = userId;
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 31 * hash + Objects.hashCode(this.pageid);
      hash = 31 * hash + Objects.hashCode(this.roleId);
      hash = 31 * hash + Objects.hashCode(this.userId);
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
      final RoleAssignmentId other = (RoleAssignmentId) obj;
      if (!Objects.equals(this.pageid, other.pageid)) {
        return false;
      }
      if (!Objects.equals(this.roleId, other.roleId)) {
        return false;
      }
      return Objects.equals(this.userId, other.userId);
    }
    
    
  }

  public RoleAssignmentId getRoleAssignmentId() {
    return roleAssignmentId;
  }

  public void setRoleAssignmentId(RoleAssignmentId roleAssignmentId) {
    this.roleAssignmentId = roleAssignmentId;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 79 * hash + Objects.hashCode(this.roleAssignmentId);
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
    final RoleAssignment other = (RoleAssignment) obj;
    return Objects.equals(this.roleAssignmentId, other.roleAssignmentId);
  }
  
  
  
  
}
