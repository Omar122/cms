 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.content.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "BLOGS")
public class Blog extends AbstractContent implements Serializable{
  
  @Column(name="TITLE")
  private String title;
  
  @Column(name="CONTENT")
  private String content;

  public Blog() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + Objects.hashCode(this.title);
    hash = 59 * hash + Objects.hashCode(this.content);
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
    final Blog other = (Blog) obj;
    if (!Objects.equals(this.title, other.title)) {
      return false;
    }
    return Objects.equals(this.content, other.content);
  }
  
  
  
}
