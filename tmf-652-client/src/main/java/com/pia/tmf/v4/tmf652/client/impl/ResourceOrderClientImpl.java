package com.pia.tmf.v4.tmf652.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf652.client.api.ResourceOrderClient;
import com.pia.tmf.v4.tmf652.exception.ResourceOrderClientException;
import com.pia.tmf.v4.tmf652.model.ResourceOrder;
import com.pia.tmf.v4.tmf652.model.ResourceOrderCreate;
import com.pia.tmf.v4.tmf652.model.ResourceOrderUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ResourceOrderClientImpl extends TmfClientBaseImpl
    <ResourceOrderCreate, ResourceOrderUpdate, ResourceOrder>
    implements ResourceOrderClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceOrder> getType() {
    return ResourceOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceOrderClientException.class;
  }

  @Override
  protected TmfClientConfig getClientConfig() {
    return this.config;
  }

  @Override
  protected WebClient getWebClient() {
    return this.webClient;
  }

  @Override
  protected TokenService getTokenService() {
    return this.tokenService;
  }

  @Override
  protected BaseClientProperties getClientProperties() {
    return this.clientProperties;
  }
}
