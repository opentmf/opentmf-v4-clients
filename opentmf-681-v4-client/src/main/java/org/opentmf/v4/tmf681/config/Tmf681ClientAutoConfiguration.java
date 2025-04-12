package org.opentmf.v4.tmf681.config;

import org.opentmf.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf681ClientAutoConfiguration {

  @Bean
  public CommunicationsMessageClientProvider communicationsMessageClientProvider(
      ApplicationContext ctx) {
    return new CommunicationsMessageClientProvider(ctx);
  }
}
