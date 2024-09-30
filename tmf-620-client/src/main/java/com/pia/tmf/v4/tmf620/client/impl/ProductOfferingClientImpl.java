package com.pia.tmf.v4.tmf620.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf620.client.api.ProductOfferingClient;
import com.pia.tmf.v4.tmf620.exception.ProductOfferingClientException;
import com.pia.tmf.v4.tmf620.model.ProductOffering;
import com.pia.tmf.v4.tmf620.model.ProductOfferingCreate;
import com.pia.tmf.v4.tmf620.model.ProductOfferingUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ProductOfferingClientImpl extends TmfClientBaseImpl
    <ProductOfferingCreate, ProductOfferingUpdate, ProductOffering>
    implements ProductOfferingClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ProductOffering> getType() {
    return ProductOffering.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ProductOfferingClientException.class;
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
