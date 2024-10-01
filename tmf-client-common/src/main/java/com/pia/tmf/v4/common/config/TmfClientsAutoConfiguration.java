package com.pia.tmf.v4.common.config;

import com.pia.client.common.service.api.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
@EnableConfigurationProperties(TmfClientConfigurations.class)
@Slf4j
public class TmfClientsAutoConfiguration implements BeanPostProcessor {

  @Bean
  public HubClientProvider hubClientProvider(ApplicationContext ctx) {
    return new HubClientProvider(ctx);
  }

  @Bean
  public GenericClientProvider genericClientProvider(ApplicationContext ctx) {
    return new GenericClientProvider(ctx);
  }
}
