/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.business.user.boundary;

import com.omar.cms.business.user.controller.UserRepository;
import com.omar.cms.business.user.entity.User;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carbo
 */
@Path("users")
public class UserController {

  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @Inject
  private UserRepository userRepository;

  @GET
  @Path("{id}")
  @Produces("application/json")
  public User findUser(@PathParam("id") Long id) {
    logger.log(Level.INFO, "Getting user by id {0}", id);
    return userRepository.findById(id)
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }

  @GET
  @Produces("application/json")
  public List<User> findAll() {
    logger.info("Getting all user");
    return userRepository.findAll();
  }

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public User create(@Valid User user) throws SQLException {
    logger.log(Level.INFO, "Creating user {0}", user.getfullName());
    try {
      return userRepository.create(user);
    } catch (PersistenceException ex) {
      logger.log(Level.INFO, "Error creating user {0}", user.getfullName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") Long id) {
    logger.log(Level.INFO, "Deleting user by id {0}", id);
    try {
      userRepository.delete(id);
    } catch (IllegalArgumentException e) {
      logger.log(Level.INFO, "Error deleting user by id {0}", id);
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }

  @PUT
  @Consumes("application/json")
  @Produces("application/json")
  public User update(@Valid User user) throws SQLException {
    logger.log(Level.INFO, "Updating user {0}", user.getfullName());
    try {
      return userRepository.create(user);
    } catch (PersistenceException ex) {
      logger.log(Level.INFO, "Error updating user {0}", user.getfullName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }
}
