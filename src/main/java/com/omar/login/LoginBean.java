package com.omar.login;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.context.FacesContext;

import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RequestScoped
@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(
        loginPage = "login/login.xhtml",
        errorPage = "login-error.xhtrml"
))
@FacesConfig
public class LoginBean {

  @Inject
  private SecurityContext securityContext;

  @Inject
  private FacesContext facesContext;

  @NotNull
  @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
  private String username;

  @NotNull
  @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
  private String password;

  public LoginBean() {
  }

  
  
  public void login() {
    Credential credential = new UsernamePasswordCredential(username, new Password(password));
    AuthenticationStatus status = securityContext
            .authenticate(getHttpRequestFromFacesContext(), (HttpServletResponse) getHttpResponseFromFacesContext(),
                    withParams().credential(credential));
    System.out.println("status" + status.toString());

    switch (status) {
      case SEND_CONTINUE:
        facesContext.responseComplete();
        return;
      case SEND_FAILURE:

        addError("Login failed");
        return;
      default:
        throw new AssertionError();
    }

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

  public HttpServletRequest getHttpResponseFromFacesContext() {
    return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getResponse();
  }

  private void addError(String login_failed) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

}
