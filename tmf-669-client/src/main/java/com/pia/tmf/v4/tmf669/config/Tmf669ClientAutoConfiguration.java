package com.pia.tmf.v4.tmf669.config;

import com.pia.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf669ClientAutoConfiguration {

  @Bean
  public PartyRoleClientProvider partyRoleClientProvider(ApplicationContext ctx) {
    return new PartyRoleClientProvider(ctx);
  }
}
