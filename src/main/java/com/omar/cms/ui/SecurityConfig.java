/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.omar.cms.ui;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author carbo
 */
@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(
        loginPage = "login.xhtml",
        errorPage = ""
       
      
))
@DatabaseIdentityStoreDefinition(
        callerQuery = "select password from users where email = ?",
        useFor = {IdentityStore.ValidationType.VALIDATE},
          
        dataSourceLookup = "jdbc/myDB",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priorityExpression = "#{100}",
        hashAlgorithmParameters = {
          "Pbkdf2PasswordHash.Iterations=3072",
          "${securityConfig.dyna}"
        }
)
@ApplicationScoped
@Named
@FacesConfig()
public class SecurityConfig {

  public String[] getDyna() {
    return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
  }
}
