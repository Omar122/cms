package com.omar.cms.ui.user;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import static jakarta.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static jakarta.security.enterprise.AuthenticationStatus.SUCCESS;
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
  private String username;

  @NotNull
  private String password;

  public LoginBean() {
  }

  public Object login() throws NamingException {

    Credential credential = new UsernamePasswordCredential(username, new Password(password));

    try {
      AuthenticationStatus status = securityContext.authenticate(getHttpRequestFromFacesContext(), getHttpResponseFromFacesContext(), withParams().credential(credential));
      switch (status) {
        case SUCCESS -> {
            logger.log(Level.SEVERE, "SUCCESS  {0}", SUCCESS.toString());
          return ("success");
        }
        case SEND_FAILURE -> {
           showValidationError("Login failed");
          logger.log(Level.SEVERE, "SEND_FAILURE  {0}", SEND_FAILURE.toString());
          return  ("failure");
        }
        case NOT_DONE -> {
          break;
        }
        case SEND_CONTINUE -> {
          facesContext.responseComplete();
        }
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Exception  {0}", e.getMessage());
    }
    return null;
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

  public void showValidationError(String content) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, content, null);
    facesContext.addMessage("", message);
    FacesContext.getCurrentInstance().addMessage(null, message);

  }
  
  public void  facetListener(AjaxBehaviorEvent event) throws AbortProcessingException{
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ajax", null);
    facesContext.
     event.getFacesContext().addMessage("",message);
  }

}
