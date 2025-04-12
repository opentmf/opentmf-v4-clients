package org.opentmf.v4.tmf666.config;

import static org.opentmf.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static org.opentmf.common.util.TmfClientConstants.TOKEN_SERVICE;
import static org.opentmf.common.util.TmfClientConstants.WEB_CLIENT;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.api.TmfClientProvider;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.v4.tmf666.client.api.BillingCycleSpecificationClient;
import org.opentmf.v4.tmf666.client.impl.BillingCycleSpecificationClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class BillingCycleSpecificationClientProvider
    implements TmfClientProvider<BillingCycleSpecificationClient> {

  private final ApplicationContext ctx;

  @Override
  public BillingCycleSpecificationClient getTmfClient(TmfClientConfig config, String clientId) {
    return new BillingCycleSpecificationClientImpl(config,
        ctx.getBean(clientId + WEB_CLIENT, WebClient.class),
        ctx.getBean(clientId + TOKEN_SERVICE, TokenService.class),
        ctx.getBean(clientId + CLIENT_PROPERTIES, BaseClientProperties.class));
  }
}
