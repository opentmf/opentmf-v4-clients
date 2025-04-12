package org.opentmf.v4.tmf633.config;

import org.opentmf.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf633ClientAutoConfiguration {

  @Bean
  public ServiceSpecificationClientProvider serviceSpecificationClientProvider(
      ApplicationContext ctx) {
    return new ServiceSpecificationClientProvider(ctx);
  }

  @Bean
  public ServiceCatalogClientProvider serviceCatalogClientProvider(ApplicationContext ctx) {
    return new ServiceCatalogClientProvider(ctx);
  }

  @Bean
  public ServiceCategoryClientProvider serviceCategoryClientProvider(ApplicationContext ctx) {
    return new ServiceCategoryClientProvider(ctx);
  }

  @Bean
  public ServiceCandidateClientProvider serviceCandidateClientProvider(ApplicationContext ctx) {
      return new ServiceCandidateClientProvider(ctx);
  }
}
