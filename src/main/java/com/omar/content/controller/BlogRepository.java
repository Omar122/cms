/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.content.controller;


import com.omar.content.entity.Blog;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carbo
 */
@Stateless
public class BlogRepository {
  
  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @PersistenceContext(name = "open")
  private EntityManager em;

  public Blog create(Blog blog) throws SQLException {
    logger.log(Level.INFO, "Creating blog {0}", blog.getTitle());
    em.persist(blog);
   return blog;
  }

  public List<Blog> findAll() {
    logger.info("Getting all blog");
    return em.createQuery("SELECT c FROM Blog c", Blog.class).getResultList();
  }

  public Optional<Blog> findById(Long id) {
    logger.log(Level.INFO, "Getting blog by id {0}", id);
    return Optional.ofNullable(em.find(Blog.class, id));
  }

  public void delete(Long id) {
    logger.log(Level.INFO, "Deleting blog by id {0}", id);
    var blog = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
    em.remove(blog);
  }

  public Blog update(Blog blog) {
    logger.log(Level.INFO, "Updating blog {0}", blog.getTitle());
    return em.merge(blog);
  }
  
}
