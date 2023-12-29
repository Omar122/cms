/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.cms.ui.user;

import com.omar.cms.business.user.controller.UserRepository;
import com.omar.cms.business.user.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
public class UserCreation {

  @Inject
  private UserRepository userRepository;

  User user;

  @PostConstruct
  public void init() {
    this.user = new User();
  }

  public void createUser() {
    try {
      this.user = userRepository.create(this.user);
     
    } catch (SQLException ex) {
      Logger.getLogger(UserCreation.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
