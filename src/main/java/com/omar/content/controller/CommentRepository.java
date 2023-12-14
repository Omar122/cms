/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.content.controller;


import com.omar.content.entity.Comment;
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
public class CommentRepository {
  
  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @PersistenceContext(name = "open")
  private EntityManager em;

  public Comment create(Comment comment) throws SQLException {
    logger.log(Level.INFO, "Creating comment {0}", comment.getId());
    em.persist(comment);
   return comment;
  }

  public List<Comment> findAll() {
    logger.info("Getting all comment");
    return em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
  }

  public Optional<Comment> findById(Long id) {
    logger.log(Level.INFO, "Getting comment by id {0}", id);
    return Optional.ofNullable(em.find(Comment.class, id));
  }

  public void delete(Long id) {
    logger.log(Level.INFO, "Deleting comment by id {0}", id);
    var comment = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + id));
    em.remove(comment);
  }

  public Comment update(Comment comment) {
    logger.log(Level.INFO, "Updating comment {0}", comment.getId());
    return em.merge(comment);
  }
}
