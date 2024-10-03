package com.pia.tmf.v4.tmf620.config;

import static com.pia.tmf.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static com.pia.tmf.common.util.TmfClientConstants.TOKEN_SERVICE;
import static com.pia.tmf.common.util.TmfClientConstants.WEB_CLIENT;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.api.TmfClientProvider;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.tmf620.client.api.ProductOfferingPriceClient;
import com.pia.tmf.v4.tmf620.client.impl.ProductOfferingPriceClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class ProductOfferingPriceClientProvider
    implements TmfClientProvider<ProductOfferingPriceClient> {

  private final ApplicationContext ctx;

  @Override
  public ProductOfferingPriceClient getTmfClient(TmfClientConfig config, String clientId) {
    return new ProductOfferingPriceClientImpl(config,
        ctx.getBean(clientId + WEB_CLIENT, WebClient.class),
        ctx.getBean(clientId + TOKEN_SERVICE, TokenService.class),
        ctx.getBean(clientId + CLIENT_PROPERTIES, BaseClientProperties.class));
  }
}
