package com.pia.tmf.v4.tmf638.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.service.model.Service;
import com.pia.tmf.v4.service.model.ServiceCreate;
import com.pia.tmf.v4.service.model.ServiceUpdate;
import com.pia.tmf.v4.tmf638.client.api.ServiceClient;
import com.pia.tmf.v4.tmf638.exception.ServiceClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Cezmi Aslan
 */
@RequiredArgsConstructor
public class ServiceClientImpl extends TmfClientBaseImpl
    <ServiceCreate, ServiceUpdate, Service>
    implements ServiceClient {

  private final TmfClientConfigurations.TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Service> getType() {
    return Service.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceClientException.class;
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
