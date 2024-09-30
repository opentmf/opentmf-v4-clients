package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.BillingCycleSpecificationClient;
import com.pia.tmf.v4.tmf666.exception.BillingCycleSpecificationClientException;
import com.pia.tmf.v4.tmf666.model.BillingCycleSpecification;
import com.pia.tmf.v4.tmf666.model.BillingCycleSpecificationCreate;
import com.pia.tmf.v4.tmf666.model.BillingCycleSpecificationUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class BillingCycleSpecificationClientImpl extends TmfClientBaseImpl
    <BillingCycleSpecificationCreate, BillingCycleSpecificationUpdate, BillingCycleSpecification>
    implements BillingCycleSpecificationClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<BillingCycleSpecification> getType() {
    return BillingCycleSpecification.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return BillingCycleSpecificationClientException.class;
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
