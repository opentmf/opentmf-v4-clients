package org.opentmf.v4.tmf634.config;

import org.opentmf.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf634ClientAutoConfiguration {

  @Bean
  public ResourceSpecificationClientProvider resourceSpecificationClientProvider(ApplicationContext ctx) {
    return new ResourceSpecificationClientProvider(ctx);
  }

  @Bean
  public ResourceCandidateClientProvider resourceCandidateClientProvider(ApplicationContext ctx) {
    return new ResourceCandidateClientProvider(ctx);
  }

  @Bean
  public ResourceCatalogClientProvider resourceCatalogClientProvider(ApplicationContext ctx) {
    return new ResourceCatalogClientProvider(ctx);
  }

  @Bean
  public ResourceCategoryClientProvider resourceCategoryClientProvider(ApplicationContext ctx) {
    return new ResourceCategoryClientProvider(ctx);
  }
}
