package com.omar.cms.business.page.entity;

import com.omar.cms.business.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "PAGES", schema = "CMS")
@NamedQueries({
  @NamedQuery(name = "Page.findAll", query = "select p from Page p")
})
public class Page implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Size(min = 4, max = 35, message = "Page title must have title")
  private String pageTitle;

  @NotEmpty
  private String about;

  private Date dateOfCreation;

  private String tagLine;

  /*
  @OneToMany
  @JoinTable(name = "user_role", schema = "CMS")
  @MapKeyJoinColumn(name = "user_id")
  private Map<User, Role> userRoles = new HashMap<>();*/
  @OneToMany(mappedBy = "page")
  Set<RoleAssignment> roleAssignments = new HashSet<>();

//Roles,Abstract Content 
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPageTitle() {
    return pageTitle;
  }

  public void setPageTitle(String pageTitle) {
    this.pageTitle = pageTitle;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public Date getDateOfCreation() {
    return dateOfCreation;
  }

  public void setDateOfCreation(Date dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  public String getTagLine() {
    return tagLine;
  }

  public void setTagLine(String tagLine) {
    this.tagLine = tagLine;
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
    if (!(object instanceof Page)) {
      return false;
    }
    Page other = (Page) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Page{" + "id=" + id + ", pageTitle=" + pageTitle + ", about=" + about + ", dateOfCreation=" + dateOfCreation + ", category=" + tagLine + '}';
  }

  public Set<RoleAssignment> getRoleAssignments() {
    return roleAssignments;
  }

  public void setRoleAssignments(Set<RoleAssignment> roleAssignments) {
    this.roleAssignments = roleAssignments;
  }

 

}
