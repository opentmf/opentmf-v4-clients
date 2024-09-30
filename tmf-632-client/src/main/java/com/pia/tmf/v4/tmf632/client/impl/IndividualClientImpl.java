package com.pia.tmf.v4.tmf632.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf632.client.api.IndividualClient;
import com.pia.tmf.v4.tmf632.exception.IndividualClientException;
import com.pia.tmf.v4.tmf632.model.Individual;
import com.pia.tmf.v4.tmf632.model.IndividualCreate;
import com.pia.tmf.v4.tmf632.model.IndividualUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class IndividualClientImpl extends TmfClientBaseImpl
    <IndividualCreate, IndividualUpdate, Individual>
    implements IndividualClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Individual> getType() {
    return Individual.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return IndividualClientException.class;
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
