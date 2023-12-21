/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.user.controller;

import com.omar.user.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout.Group;

/**
 *
 * @author carbo
 */
@Stateless
public class UserRepository {

  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @PersistenceContext(name = "open")
  private EntityManager em;

  public User create(User user) throws SQLException {
    logger.log(Level.INFO, "Creating user {0}", user.toString());
    em.persist(user);
    return user;
  }

  public List<User> findAll() {
    logger.info("Getting all user");
    return em.createQuery("SELECT c FROM User c", User.class).getResultList();
  }

  public Optional<User> findById(Long id) {
    logger.log(Level.INFO, "Getting user by id {0}", id);
    return Optional.ofNullable(em.find(User.class, id));
  }

  public void delete(Long id) {
    logger.log(Level.INFO, "Deleting user by id {0}", id);
    var user = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    em.remove(user);
  }

  public User update(User user) {
    logger.log(Level.INFO, "Updating user {0}", user.getfullName());
    return em.merge(user);
  }

  public Optional<User> findByUsernameAndPassword(String username, String password) {
    User user = findByUsername(username).orElseThrow();

    if (!user.getPassword().equals(password)) {
      throw new NoResultException("password wrong");
    }
    return Optional.of(user);
  }

  private Optional<User> findByUsername(String username) {
    try {
      return Optional.of(em
              .createQuery("FROM User u WHERE u.username = :username", User.class)
              .setParameter("username", username)
              .getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

}
