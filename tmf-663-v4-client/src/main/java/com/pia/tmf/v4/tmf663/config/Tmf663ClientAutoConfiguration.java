package com.pia.tmf.v4.tmf663.config;

import com.pia.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf663ClientAutoConfiguration {

  @Bean
  public ShoppingCartClientProvider partyRoleClientProvider(ApplicationContext ctx) {
    return new ShoppingCartClientProvider(ctx);
  }
}
