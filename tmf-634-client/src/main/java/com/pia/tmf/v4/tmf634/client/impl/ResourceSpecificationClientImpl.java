package com.pia.tmf.v4.tmf634.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf634.client.api.ResourceSpecificationClient;
import com.pia.tmf.v4.tmf634.exception.ResourceSpecificationClientException;
import com.pia.tmf.v4.tmf634.model.ResourceSpecification;
import com.pia.tmf.v4.tmf634.model.ResourceSpecificationCreate;
import com.pia.tmf.v4.tmf634.model.ResourceSpecificationUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ResourceSpecificationClientImpl extends TmfClientBaseImpl
    <ResourceSpecificationCreate, ResourceSpecificationUpdate, ResourceSpecification>
    implements ResourceSpecificationClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceSpecification> getType() {
    return ResourceSpecification.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceSpecificationClientException.class;
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
