package com.pia.tmf.v4.tmf633.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf633.client.api.ServiceSpecificationClient;
import com.pia.tmf.v4.tmf633.exception.ServiceSpecificationClientException;
import com.pia.tmf.v4.tmf633.model.ServiceSpecification;
import com.pia.tmf.v4.tmf633.model.ServiceSpecificationCreate;
import com.pia.tmf.v4.tmf633.model.ServiceSpecificationUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class ServiceSpecificationClientImpl extends TmfClientBaseImpl
    <ServiceSpecificationCreate, ServiceSpecificationUpdate, ServiceSpecification>
    implements ServiceSpecificationClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceSpecification> getType() {
    return ServiceSpecification.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceSpecificationClientException.class;
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
