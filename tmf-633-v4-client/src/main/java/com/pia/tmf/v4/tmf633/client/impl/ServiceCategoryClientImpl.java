package com.pia.tmf.v4.tmf633.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf633.client.api.ServiceCategoryClient;
import com.pia.tmf.v4.tmf633.exception.ServiceCategoryClientException;
import com.pia.tmf.v4.tmf633.model.ServiceCategory;
import com.pia.tmf.v4.tmf633.model.ServiceCategoryCreate;
import com.pia.tmf.v4.tmf633.model.ServiceCategoryUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class ServiceCategoryClientImpl extends TmfClientBaseImpl
    <ServiceCategoryCreate, ServiceCategoryUpdate, ServiceCategory>
    implements ServiceCategoryClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceCategory> getType() {
    return ServiceCategory.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceCategoryClientException.class;
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
