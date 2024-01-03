/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.cms.business.user.boundary;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.net.URL;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


/**
 *
 * @author carbo
 */
@ExtendWith(ArquillianExtension.class)
public class UserContollerIT {

  private static final String WARNAME = System.getProperty("arquillian.war.name");

  @ArquillianResource
  private URL baseURL;

  private final String INVENTORY_SYSTEMS = "api";
  private Client client = ClientBuilder.newClient();

  @Inject
  UserController UserController;

  @Deployment
  public static WebArchive createDeployment() {
    System.err.println("WARNAME: "+WARNAME);
    WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
            .addPackages(true, "com.omar.cms");
    System.out.println("archive:");
    System.out.println(archive.toString(true));
    return archive;
  }

  //@Test
  @RunAsClient
 // @InSequence(1)
  @Test
  public void testInventoryEndpoints() throws Exception {
    String localhosturl = baseURL + INVENTORY_SYSTEMS + "/users";
    System.err.println("localhosturl:"+localhosturl);
    WebTarget localhosttarget = client.target(localhosturl);
    try (Response localhostresponse = localhosttarget.request().get()) {
      Assert.assertEquals(200,
              localhostresponse.getStatus());
      
      JsonObject localhostobj = localhostresponse.readEntity(JsonObject.class);
      Assert.assertEquals("The system property for the local and remote JVM "
              + "should match", System.getProperty("os.name"),
              localhostobj.getString("os.name"));
      
      String invsystemsurl = baseURL + INVENTORY_SYSTEMS;
      
      WebTarget invsystemstarget = client.target(invsystemsurl);
      Response invsystemsresponse = invsystemstarget.request().get();
      
      Assert.assertEquals("Incorrect response code from " + localhosturl, 200,
              invsystemsresponse.getStatus());
      
      JsonObject invsystemsobj = invsystemsresponse.readEntity(JsonObject.class);
      
      int expected = 1;
      int actual = invsystemsobj.getInt("total");
      Assert.assertEquals("The inventory should have one entry for localhost",
              expected, actual);
    }
  }

}
