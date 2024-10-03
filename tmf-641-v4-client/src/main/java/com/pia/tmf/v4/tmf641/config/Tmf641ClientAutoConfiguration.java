package com.pia.tmf.v4.tmf641.config;

import com.pia.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Pablo Garcia
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf641ClientAutoConfiguration {

  @Bean
  public ServiceOrderClientProvider serviceOrderClientProvider(ApplicationContext ctx) {
    return new ServiceOrderClientProvider(ctx);
  }

  @Bean
  public CancelServiceOrderClientProvider cancelServiceOrderClientProvider(ApplicationContext ctx) {
    return new CancelServiceOrderClientProvider(ctx);
  }
}
