/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.cms.business.user.boundary;

import com.omar.cms.business.user.controller.UserRepository;
import com.omar.cms.business.user.entity.User;
import jakarta.inject.Inject;
import jakarta.transaction.UserTransaction;
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
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

  @Inject
  UserTransaction utx;

  @Inject
  UserRepository userRepository;

  @Deployment
  public static WebArchive createDeployment() {
    System.err.println("WARNAME: " + WARNAME);
    WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
            .addPackages(true, "com.omar.cms.business")
            .addAsResource(new ClassLoaderAsset("META-INF/persistence.xml"), "META-INF/persistence.xml");

    System.out.println("archive:");
    System.out.println(archive.toString(true));
    return archive;
  }

  //@Test
  @RunAsClient
  // @InSequence(1)
  @Test
  public void testInventoryEndpoints() throws Exception {
     System.err.println("base:" + baseURL);
    String localhosturl = baseURL + INVENTORY_SYSTEMS + "/users";
    System.err.println("localhosturl:" + localhosturl);
    WebTarget localhosttarget = client.target(localhosturl);
    try (Response localhostresponse = localhosttarget.request().get()) {
      Assert.assertEquals(200,
              localhostresponse.getStatus());
    }
  }

  @Test
  public void DdIntegrationTesting() throws Exception {
    utx.begin();
    userRepository.getEm().joinTransaction();
    userRepository.create(new User("Omar Ajab Alfuraydi", "214233", "0550526499", "omar@gmail.com"));
    utx.commit();
    var userdb = userRepository.findByEmail("omar@gmail.com");
    System.out.println("tosriting " + userdb.toString());
    assertNotNull(userdb);
  }

}
