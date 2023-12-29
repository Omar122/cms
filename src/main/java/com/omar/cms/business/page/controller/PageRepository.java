/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.cms.business.page.controller;

import com.omar.cms.business.page.entity.Page;
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
public class PageRepository {

  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @PersistenceContext(name = "open")
  private EntityManager em;

  public Page create(Page page) throws SQLException {
    logger.log(Level.INFO, "Creating page {0}", page.getPageTitle());
    em.persist(page);
   return page;
  }

  public List<Page> findAll() {
    logger.info("Getting all page");
    return em.createQuery("SELECT c FROM Page c", Page.class).getResultList();
  }

  public Optional<Page> findById(Long id) {
    logger.log(Level.INFO, "Getting page by id {0}", id);
    return Optional.ofNullable(em.find(Page.class, id));
  }

  public void delete(Long id) {
    logger.log(Level.INFO, "Deleting page by id {0}", id);
    var page = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid page Id:" + id));
    em.remove(page);
  }

  public Page update(Page page) {
    logger.log(Level.INFO, "Updating page {0}", page.getPageTitle());
    return em.merge(page);
  }
}
