package com.pia.tmf.v4.tmf633.config;

import com.pia.client.openid.config.OpenidWebClientProviderAutoConfiguration;
import com.pia.client.openid.model.OpenidClientProperties;
import com.pia.client.openid.model.OpenidClients;
import com.pia.client.openid.service.api.OpenidTokenService;
import com.pia.client.openid.service.api.OpenidWebClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OpenidClients.class)
@Import(OpenidWebClientProviderAutoConfiguration.class)
public class OpenidAuthClientsConfig {

  private final OpenidWebClientProvider openidWebClientProvider;
  private final OpenidClients openidClients;

  @Bean
  public OpenidClientProperties shClientProperties() {
    return openidClients.getOpenid().get("sh-client");
  }

  @Bean
  public WebClient shWebClient(@Qualifier("shClientProperties") OpenidClientProperties shClientProperties) {
    return openidWebClientProvider.buildWebClient(shClientProperties);
  }

  @Bean
  public OpenidTokenService shTokenService(@Qualifier("shClientProperties") OpenidClientProperties shClientProperties) {
    return openidWebClientProvider.buildTokenService(shClientProperties);
  }

  @Bean
  public OpenidClientProperties apixClientProperties() {
    return openidClients.getOpenid().get("apix-client");
  }

  @Bean
  public WebClient apixWebClient(@Qualifier("apixClientProperties") OpenidClientProperties apixClientProperties) {
    return openidWebClientProvider.buildWebClient(apixClientProperties);
  }

  @Bean
  public OpenidTokenService apixTokenService(@Qualifier("apixClientProperties") OpenidClientProperties apixClientProperties) {
    return openidWebClientProvider.buildTokenService(apixClientProperties);
  }
}
