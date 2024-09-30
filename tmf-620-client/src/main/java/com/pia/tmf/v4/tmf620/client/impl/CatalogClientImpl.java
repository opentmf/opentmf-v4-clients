package com.pia.tmf.v4.tmf620.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf620.client.api.CatalogClient;
import com.pia.tmf.v4.tmf620.exception.CatalogClientException;
import com.pia.tmf.v4.tmf620.model.Catalog;
import com.pia.tmf.v4.tmf620.model.CatalogCreate;
import com.pia.tmf.v4.tmf620.model.CatalogUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class CatalogClientImpl extends TmfClientBaseImpl
    <CatalogCreate, CatalogUpdate, Catalog>
    implements CatalogClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Catalog> getType() {
    return Catalog.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CatalogClientException.class;
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
