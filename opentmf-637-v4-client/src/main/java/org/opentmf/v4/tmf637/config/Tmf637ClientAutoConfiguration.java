package org.opentmf.v4.tmf637.config;

import org.opentmf.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf637ClientAutoConfiguration {

  @Bean
  public ProductClientProvider productInventoryClientProvider(ApplicationContext ctx) {
    return new ProductClientProvider(ctx);
  }
}
