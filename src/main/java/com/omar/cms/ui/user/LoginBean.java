package com.omar.cms.ui.user;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import static jakarta.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import jakarta.security.enterprise.SecurityContext;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Model
public class LoginBean {

  private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @Inject
  SecurityContext securityContext;

  @Inject
  FacesContext facesContext;

  @NotNull
  //@Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
  private String username;

  @NotNull
  //@Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
  private String password;


  public LoginBean() {
  }

  public String login() throws NamingException {

    Credential credential = new UsernamePasswordCredential(username, new Password(password));

    try {
      AuthenticationStatus status = securityContext.authenticate(getHttpRequestFromFacesContext(), getHttpResponseFromFacesContext(), withParams().credential(credential));
      System.out.println("status"+status);
      switch (status) {
        case SUCCESS-> {
          facesContext.responseComplete();
          return "index.xhtml";
        }
        case SEND_FAILURE -> {
          addError("Login failed");
          facesContext.responseComplete();
          logger.log(Level.SEVERE, "SEND_CONTINUE  {0}", SEND_FAILURE.toString());
          return null;
        }
        default ->

          throw new AssertionError();

      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Exception  {0}", e.getMessage());
    }
    return "";
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public HttpServletRequest getHttpRequestFromFacesContext() {
    return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
  }

  public HttpServletResponse getHttpResponseFromFacesContext() {
    return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
  }

  private void addError(String login_failed) {
    facesContext.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, username, login_failed));
  }

}
