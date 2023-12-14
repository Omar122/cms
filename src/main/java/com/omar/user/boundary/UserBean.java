/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.user.boundary;


import com.omar.user.controller.UserRepository;
import com.omar.user.entity.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@RequestScoped
@Named(value = "userBean")
public class UserBean {

  @Inject
  private UserRepository userRepository;

  @NotNull
  @NotBlank
  private String fullName;

  @NotNull
  @NotBlank
  private String userName;

  @NotNull
  @NotBlank
  private String password;

  @NotNull
  @NotBlank
  private String mobileNumber;

  @NotNull
  @NotBlank
  @Email(message = "Please Enter a valid  Email address")
  private String email;
  
  private  User user= new User();

  public String getUser() {
    return user.toString();
  }
  
  
  
  public void createUser() {
    try {
      this.user =userRepository.create(new User(fullName, userName, password, mobileNumber, email));
    } catch (SQLException ex) {
      Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  

}
