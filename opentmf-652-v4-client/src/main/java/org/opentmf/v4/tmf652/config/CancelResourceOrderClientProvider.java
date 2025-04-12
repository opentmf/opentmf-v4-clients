package org.opentmf.v4.tmf652.config;

import static org.opentmf.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static org.opentmf.common.util.TmfClientConstants.TOKEN_SERVICE;
import static org.opentmf.common.util.TmfClientConstants.WEB_CLIENT;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.api.TmfClientProvider;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.v4.tmf652.client.api.CancelResourceOrderClient;
import org.opentmf.v4.tmf652.client.impl.CancelResourceOrderClientImpl;
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
