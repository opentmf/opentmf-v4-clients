package com.pia.tmf.v4.tmf634.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf634.client.api.ResourceCandidateClient;
import com.pia.tmf.v4.tmf634.exception.ResourceCandidateClientException;
import com.pia.tmf.v4.tmf634.model.ResourceCandidate;
import com.pia.tmf.v4.tmf634.model.ResourceCandidateCreate;
import com.pia.tmf.v4.tmf634.model.ResourceCandidateUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ResourceCandidateClientImpl extends TmfClientBaseImpl
    <ResourceCandidateCreate, ResourceCandidateUpdate, ResourceCandidate>
    implements ResourceCandidateClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceCandidate> getType() {
    return ResourceCandidate.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceCandidateClientException.class;
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
