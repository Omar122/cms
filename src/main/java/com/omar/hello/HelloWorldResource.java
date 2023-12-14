package com.omar.hello;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("hello")
public class HelloWorldResource {

  //@Inject
 // private UserRepository userRepository;

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Hello hello(@QueryParam("name") String name) {
    if ((name == null) || name.trim().isEmpty()) {
      name = "world";
    }
   // userRepository.create(new User(Long.valueOf(1), "Omar Ajab", "oalfudyi", "2142", "0555052", "carboom@stc.com.sa"));

    return new Hello(name);
  }
}
