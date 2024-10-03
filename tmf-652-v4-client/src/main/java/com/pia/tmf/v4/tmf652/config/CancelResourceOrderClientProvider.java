package com.pia.tmf.v4.tmf652.config;

import static com.pia.tmf.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static com.pia.tmf.common.util.TmfClientConstants.TOKEN_SERVICE;
import static com.pia.tmf.common.util.TmfClientConstants.WEB_CLIENT;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.api.TmfClientProvider;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.tmf652.client.api.CancelResourceOrderClient;
import com.pia.tmf.v4.tmf652.client.impl.CancelResourceOrderClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class CancelResourceOrderClientProvider
    implements TmfClientProvider<CancelResourceOrderClient> {

  private final ApplicationContext ctx;

  @Override
  public CancelResourceOrderClient getTmfClient(TmfClientConfig config, String clientId) {
    return new CancelResourceOrderClientImpl(config,
        ctx.getBean(clientId + WEB_CLIENT, WebClient.class),
        ctx.getBean(clientId + TOKEN_SERVICE, TokenService.class),
        ctx.getBean(clientId + CLIENT_PROPERTIES, BaseClientProperties.class));
  }
}
