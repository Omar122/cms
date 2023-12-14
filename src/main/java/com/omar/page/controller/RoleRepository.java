/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.page.controller;


import com.omar.page.entity.Role;
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
public class RoleRepository {

  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @PersistenceContext(name = "open")
  private EntityManager em;

  public Role create(Role role) throws SQLException {
    logger.log(Level.INFO, "Creating role {0} for user user {1} and page {2}", new Object[]{role.getRoleEnum(), role.getUser().getUserName(), role.getPage().getPageTitle()});
    em.persist(role);
    return role;
  }

  public List<Role> findAll() {
    logger.info("Getting all role");
    return em.createQuery("SELECT c FROM Role c", Role.class).getResultList();
  }

  public Optional<Role> findById(Long id) {
    logger.log(Level.INFO, "Getting role by id {0}", id);
    return Optional.ofNullable(em.find(Role.class, id));
  }

  public void delete(Long id) {
    logger.log(Level.INFO, "Deleting role by id {0}", id);
    var role = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
    em.remove(role);
  }

  public Role update(Role role) {
    logger.log(Level.INFO, "updated role {0} for user user {1} and page {2}", new Object[]{role.getRoleEnum(), role.getUser().getUserName(), role.getPage().getPageTitle()});
    return em.merge(role);
  }
}
