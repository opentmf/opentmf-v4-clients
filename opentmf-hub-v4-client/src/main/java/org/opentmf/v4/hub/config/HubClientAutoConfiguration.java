package org.opentmf.v4.hub.config;

import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.config.TmfClientConfigurations;
import lombok.extern.slf4j.Slf4j;
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
public class HubClientAutoConfiguration {

  @Bean
  public HubClientProvider hubClientProvider(ApplicationContext ctx) {
    return new HubClientProvider(ctx);
  }
}
