package org.opentmf.v4.tmf652.config;

import org.opentmf.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf652ClientAutoConfiguration {

  @Bean
  public ResourceOrderClientProvider resourceOrderProvider(ApplicationContext ctx) {
    return new ResourceOrderClientProvider(ctx);
  }

  @Bean
  public CancelResourceOrderClientProvider cancelResourceOrderProvider(ApplicationContext ctx) {
    return new CancelResourceOrderClientProvider(ctx);
  }
}
