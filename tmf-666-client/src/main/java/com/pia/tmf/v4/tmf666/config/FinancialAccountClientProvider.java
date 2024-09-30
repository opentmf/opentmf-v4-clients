package com.pia.tmf.v4.tmf666.config;

import static com.pia.tmf.v4.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static com.pia.tmf.v4.common.util.TmfClientConstants.TOKEN_SERVICE;
import static com.pia.tmf.v4.common.util.TmfClientConstants.WEB_CLIENT;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.TmfClientProvider;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.tmf666.client.api.FinancialAccountClient;
import com.pia.tmf.v4.tmf666.client.impl.FinancialAccountClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class FinancialAccountClientProvider
    implements TmfClientProvider<FinancialAccountClient> {

  private final ApplicationContext ctx;

  @Override
  public FinancialAccountClient getTmfClient(TmfClientConfig config, String clientId) {
    return new FinancialAccountClientImpl(config,
        ctx.getBean(clientId + WEB_CLIENT, WebClient.class),
        ctx.getBean(clientId + TOKEN_SERVICE, TokenService.class),
        ctx.getBean(clientId + CLIENT_PROPERTIES, BaseClientProperties.class));
  }
}
