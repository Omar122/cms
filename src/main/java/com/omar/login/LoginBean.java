package com.omar.login;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Named(value = "loginBean")
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

  public String login() {
    Credential credential = new UsernamePasswordCredential(username, new Password(password));
    // logger.log(Level.INFO, "getCallerPrincipal  {0}", securityContext.getCallerPrincipal().toString());

    try {
      AuthenticationStatus status = securityContext.authenticate(getHttpRequestFromFacesContext(), getHttpResponseFromFacesContext(), withParams().credential(credential));
      switch (status) {
        case SEND_CONTINUE -> {
          facesContext.responseComplete();
          return "usercreation.xhtml";
        }
        case SEND_FAILURE -> {
          addError("Login failed");
          return "usercreation.xhtml";
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
    facesContext.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_ERROR, username, "incorrect login"));
  }

}
