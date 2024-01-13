/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.cms.business.page.controller;

import com.omar.cms.business.page.entity.RoleAssignment;
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
public class RoleAssignmentRepository {

  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @PersistenceContext(name = "open")
  private EntityManager em;

  public RoleAssignment create(RoleAssignment roleAssignment) throws SQLException {
    em.persist(roleAssignment);
    logger.info("create all roleAssignmentAssignment");
    return roleAssignment;
  }

  public List<RoleAssignment> findAll() {
    logger.info("Getting all roleAssignmentAssignment");
    return em.createQuery("SELECT c FROM RoleAssignment c", RoleAssignment.class).getResultList();
  }

  public Optional<RoleAssignment> findById(Long id) {
    logger.log(Level.INFO, "Getting roleAssignment by id {0}", id);
    return Optional.ofNullable(em.find(RoleAssignment.class, id));
  }

  public void delete(Long id) {
    var roleAssignment = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid roleAssignment Id:" + id));
    em.remove(roleAssignment);
  }

  public RoleAssignment update(RoleAssignment roleAssignment) {
    return em.merge(roleAssignment);
  }
}
