/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.content.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "Comments")
public class Comment implements Serializable {

  public Comment() {
  }

  
  
  @Id
  @GeneratedValue
  private long id;

  @Column(name = "DATE")
  private Date date;

  @Email
  private String email;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BLOG_ID")
  private Blog blog;

  @Column(name = "text")
  private String text;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 23 * hash + Objects.hashCode(this.date);
    hash = 23 * hash + Objects.hashCode(this.email);
    hash = 23 * hash + Objects.hashCode(this.blog);
    hash = 23 * hash + Objects.hashCode(this.text);
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
    final Comment other = (Comment) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.email, other.email)) {
      return false;
    }
    if (!Objects.equals(this.text, other.text)) {
      return false;
    }
    if (!Objects.equals(this.date, other.date)) {
      return false;
    }
    return Objects.equals(this.blog, other.blog);
  }
    
  
  

}
