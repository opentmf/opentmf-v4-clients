package com.pia.tmf.v4.tmf648.config;

import com.pia.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf648ClientAutoConfiguration {

  @Bean
  public QuoteClientProvider productOrderClientProvider(ApplicationContext ctx) {
    return new QuoteClientProvider(ctx);
  }
}
