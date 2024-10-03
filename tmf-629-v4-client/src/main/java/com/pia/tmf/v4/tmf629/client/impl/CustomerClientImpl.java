package com.pia.tmf.v4.tmf629.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf629.client.api.CustomerClient;
import com.pia.tmf.v4.tmf629.exception.CustomerClientException;
import com.pia.tmf.v4.tmf629.model.Customer;
import com.pia.tmf.v4.tmf629.model.CustomerCreate;
import com.pia.tmf.v4.tmf629.model.CustomerUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class CustomerClientImpl extends TmfClientBaseImpl
    <CustomerCreate, CustomerUpdate, Customer>
    implements CustomerClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Customer> getType() {
    return Customer.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CustomerClientException.class;
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
