package com.pia.tmf.v4.tmf633.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf633.client.api.ServiceCatalogClient;
import com.pia.tmf.v4.tmf633.exception.ServiceCatalogClientException;
import com.pia.tmf.v4.tmf633.model.ServiceCatalog;
import com.pia.tmf.v4.tmf633.model.ServiceCatalogCreate;
import com.pia.tmf.v4.tmf633.model.ServiceCatalogUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class ServiceCatalogClientImpl extends TmfClientBaseImpl
    <ServiceCatalogCreate, ServiceCatalogUpdate, ServiceCatalog>
    implements ServiceCatalogClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceCatalog> getType() {
    return ServiceCatalog.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceCatalogClientException.class;
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
