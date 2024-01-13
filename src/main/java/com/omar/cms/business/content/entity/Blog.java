/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.content.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "BLOGS", schema = "CMS")
@NamedQueries({
  @NamedQuery(name = "Blog.findByUser", query = "select b from Blog b where b.user = :user"),
  @NamedQuery(name = "Blog.findByPage", query = "select b from Blog b where b.page = :page"),
  @NamedQuery(name = "Blog.findByTagName", query = "select b from Blog b inner join b.tagsList tagsList where tagsList.tagName = :tagName")
})
public class Blog extends AbstractContent implements Serializable {

  @Column(name = "TITLE")
  private String title;

  @Column(name = "CONTENT")
  private String content;

//Dont use mnaytomany as list
  //https://thorben-janssen.com/hibernate-performance-tuning/ 
  @ManyToMany
  @JoinTable(
          name = "BLOG_TAGS",
          schema = "CMS",
          joinColumns
          = @JoinColumn(name = "BLOG_ID", referencedColumnName = "ID"),
          inverseJoinColumns
          = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID")
  )
  Set<Tag> tagsList;

  @OneToMany(mappedBy = "Blog", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

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

  public Set<Tag> getTagsList() {
    return tagsList;
  }

  public void setTagsList(Set<Tag> tagsList) {
    this.tagsList = tagsList;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void addComments(Comment comment) {
    comments.add(comment);
    comment.setBlog(this);
  }

  public void removeComments(Comment comment) {
    comments.remove(comment);
    comment.setBlog(null);
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
