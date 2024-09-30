package com.pia.tmf.v4.tmf632.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf632.client.api.OrganizationClient;
import com.pia.tmf.v4.tmf632.exception.OrganizationClientException;
import com.pia.tmf.v4.tmf632.model.Organization;
import com.pia.tmf.v4.tmf632.model.OrganizationCreate;
import com.pia.tmf.v4.tmf632.model.OrganizationUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class OrganizationClientImpl extends TmfClientBaseImpl
    <OrganizationCreate, OrganizationUpdate, Organization>
    implements OrganizationClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Organization> getType() {
    return Organization.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return OrganizationClientException.class;
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
