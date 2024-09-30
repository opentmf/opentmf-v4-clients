package com.pia.tmf.v4.tmf620.config;

import com.pia.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf620ClientAutoConfiguration {

  @Bean
  public ProductSpecificationClientProvider productSpecificationClientProvider(ApplicationContext ctx) {
    return new ProductSpecificationClientProvider(ctx);
  }

  @Bean
  public ProductOfferingClientProvider productOfferingClientProvider(ApplicationContext ctx) {
    return new ProductOfferingClientProvider(ctx);
  }

  @Bean
  public ProductOfferingPriceClientProvider productOfferingPriceClientProvider(ApplicationContext ctx) {
    return new ProductOfferingPriceClientProvider(ctx);
  }

  @Bean
  public CatalogClientProvider catalogClientProvider(ApplicationContext ctx) {
    return new CatalogClientProvider(ctx);
  }

  @Bean
  public CategoryClientProvider categoryClientProvider(ApplicationContext ctx) {
    return new CategoryClientProvider(ctx);
  }
}
