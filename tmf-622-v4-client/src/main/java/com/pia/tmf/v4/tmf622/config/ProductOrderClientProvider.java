package com.pia.tmf.v4.tmf622.config;

import static com.pia.tmf.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static com.pia.tmf.common.util.TmfClientConstants.TOKEN_SERVICE;
import static com.pia.tmf.common.util.TmfClientConstants.WEB_CLIENT;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.api.TmfClientProvider;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.tmf622.client.api.ProductOrderClient;
import com.pia.tmf.v4.tmf622.client.impl.ProductOrderClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class ProductOrderClientProvider implements TmfClientProvider<ProductOrderClient> {

  private final ApplicationContext ctx;

  @Override
  public ProductOrderClient getTmfClient(TmfClientConfig config, String clientId) {
    return new ProductOrderClientImpl(config,
        ctx.getBean(clientId + WEB_CLIENT, WebClient.class),
        ctx.getBean(clientId + TOKEN_SERVICE, TokenService.class),
        ctx.getBean(clientId + CLIENT_PROPERTIES, BaseClientProperties.class));
  }
}
