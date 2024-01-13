/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.content.entity;

import java.util.Date;

import jakarta.persistence.*;

/**
 *
 * @author carbo
 */
@Entity
@Table(name = "BLOGSREVS", schema = "CMS")
@NamedQueries({
        @NamedQuery(name = "BlogRevision.findByBlog_Id", query = "select b from BlogRevision b where b.blog.id = :id"),
        @NamedQuery(name = "BlogRevision.findByBlog_User", query = "select b from BlogRevision b where b.blog.user = :user"),
        @NamedQuery(name = "BlogRevision.findByPage", query = "select b from BlogRevision b where b.page = :page")
})
public class BlogRevision extends AbstractContent {

  
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "BLOG_ID")
  private Blog blog;

  @Column(name = "DATE")
  private Date date;
  
  @Column(name = "BlogType")
  @Enumerated(EnumType.STRING)
  private BlogType Type;

  /* LAter 
  @Column(name = "Comment")
  private String Comment;*/

  public BlogType getType() {
    return Type;
  }

  public void setType(BlogType Type) {
    this.Type = Type;
  }


  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
