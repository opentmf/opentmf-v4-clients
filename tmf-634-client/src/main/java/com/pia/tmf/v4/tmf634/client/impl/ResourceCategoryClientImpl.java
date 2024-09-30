package com.pia.tmf.v4.tmf634.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf634.client.api.ResourceCategoryClient;
import com.pia.tmf.v4.tmf634.exception.ResourceCategoryClientException;
import com.pia.tmf.v4.tmf634.model.ResourceCategory;
import com.pia.tmf.v4.tmf634.model.ResourceCategoryCreate;
import com.pia.tmf.v4.tmf634.model.ResourceCategoryUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ResourceCategoryClientImpl extends TmfClientBaseImpl
    <ResourceCategoryCreate, ResourceCategoryUpdate, ResourceCategory>
    implements ResourceCategoryClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceCategory> getType() {
    return ResourceCategory.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceCategoryClientException.class;
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
