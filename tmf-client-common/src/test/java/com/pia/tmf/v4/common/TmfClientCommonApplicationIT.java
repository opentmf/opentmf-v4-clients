package com.pia.tmf.v4.common;

import com.pia.tmf.v4.common.model.ExtendedEventSubscription;
import java.net.URI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@SpringBootTest
class TmfClientCommonApplicationIT {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private WebClient shWebClient;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(applicationContext);
    Assertions.assertNotNull(shWebClient);
    var extendedSubscription = new ExtendedEventSubscription();
    var uri = URI.create("http://callmeback");
    extendedSubscription.setHubUri(uri);
    Assertions.assertTrue(
        extendedSubscription.toString().contains(
            extendedSubscription.getHubUri().toString()));
  }
}
