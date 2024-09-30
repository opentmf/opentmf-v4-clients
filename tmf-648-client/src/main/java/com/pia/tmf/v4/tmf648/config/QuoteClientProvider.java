package com.pia.tmf.v4.tmf648.config;

import static com.pia.tmf.v4.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static com.pia.tmf.v4.common.util.TmfClientConstants.TOKEN_SERVICE;
import static com.pia.tmf.v4.common.util.TmfClientConstants.WEB_CLIENT;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.TmfClientProvider;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.tmf648.client.api.QuoteClient;
import com.pia.tmf.v4.tmf648.client.impl.QuoteClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class QuoteClientProvider implements TmfClientProvider<QuoteClient> {

  private final ApplicationContext ctx;

  @Override
  public QuoteClient getTmfClient(TmfClientConfig config, String clientId) {
    return new QuoteClientImpl(config,
        ctx.getBean(clientId + WEB_CLIENT, WebClient.class),
        ctx.getBean(clientId + TOKEN_SERVICE, TokenService.class),
        ctx.getBean(clientId + CLIENT_PROPERTIES, BaseClientProperties.class));
  }
}
