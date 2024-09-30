package com.pia.tmf.v4.tmf639.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.resource.model.Resource;
import com.pia.tmf.v4.resource.model.ResourceCreate;
import com.pia.tmf.v4.resource.model.ResourceUpdate;
import com.pia.tmf.v4.tmf639.client.api.ResourceClient;
import com.pia.tmf.v4.tmf639.exception.ResourceClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ResourceClientImpl extends TmfClientBaseImpl
    <ResourceCreate, ResourceUpdate, Resource>
    implements ResourceClient {

  private final TmfClientConfigurations.TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Resource> getType() {
    return Resource.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceClientException.class;
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
