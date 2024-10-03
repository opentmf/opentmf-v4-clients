package com.pia.tmf.v4.tmf622.config;

import com.pia.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf622ClientAutoConfiguration {

  @Bean
  public ProductOrderClientProvider productOrderClientProvider(ApplicationContext ctx) {
    return new ProductOrderClientProvider(ctx);
  }

  @Bean
  public CancelProductOrderClientProvider cancelProductOrderClient(ApplicationContext ctx) {
    return new CancelProductOrderClientProvider(ctx);
  }
}
