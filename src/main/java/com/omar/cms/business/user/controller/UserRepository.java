/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.user.controller;

import com.omar.cms.business.user.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carbo
 */
@Stateless
public class UserRepository {

  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @PersistenceContext(name = "open")
  private EntityManager em;

  @Inject
  private Pbkdf2PasswordHash passwordHash;

  @PostConstruct
  private void init() {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
    parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
    parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
    passwordHash.initialize(parameters);
  }

  public User create(User user) throws SQLException {
    logger.log(Level.INFO, "Creating user {0}", user.toString());
    user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
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

  public Optional<User> findByEmail(String email) {
    logger.log(Level.INFO, "Getting user by email {0}", email);
    return Optional.ofNullable((User) em.createNamedQuery(User.findUserByEmail).setParameter("email", email).getSingleResult());
  }

  public void delete(Long id) {
    logger.log(Level.INFO, "Deleting user by id {0}", id);
    var user = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    em.remove(user);
  }

  public User update(User user) {
    logger.log(Level.INFO, "Updating user {0}", user.getfullName());
    try {
      user=em.merge(user);
    } catch (Exception e) {
       logger.log(Level.SEVERE, "Error Updaing user"+user.getEmail(), e);
    }
    return user ;
  }

  public EntityManager getEm() {
    return em;
  }

  public void setEm(EntityManager em) {
    this.em = em;
  }

}
