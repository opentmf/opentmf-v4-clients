package org.opentmf.v4.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Cezmi Aslan
 */
@SpringBootApplication
public class TmfHubClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(TmfHubClientApplication.class, args);
  }

}
