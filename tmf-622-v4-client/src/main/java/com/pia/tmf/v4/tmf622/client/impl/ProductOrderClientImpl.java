package com.pia.tmf.v4.tmf622.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf622.client.api.ProductOrderClient;
import com.pia.tmf.v4.tmf622.exception.ProductOrderClientException;
import com.pia.tmf.v4.tmf622.model.ProductOrder;
import com.pia.tmf.v4.tmf622.model.ProductOrderCreate;
import com.pia.tmf.v4.tmf622.model.ProductOrderUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ProductOrderClientImpl extends TmfClientBaseImpl
    <ProductOrderCreate, ProductOrderUpdate, ProductOrder>
    implements ProductOrderClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ProductOrder> getType() {
    return ProductOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ProductOrderClientException.class;
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
