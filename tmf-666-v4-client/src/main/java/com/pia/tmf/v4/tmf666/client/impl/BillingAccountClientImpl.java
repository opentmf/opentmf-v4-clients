package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.BillingAccountClient;
import com.pia.tmf.v4.tmf666.exception.BillingAccountClientException;
import com.pia.tmf.v4.tmf666.model.BillingAccount;
import com.pia.tmf.v4.tmf666.model.BillingAccountCreate;
import com.pia.tmf.v4.tmf666.model.BillingAccountUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class BillingAccountClientImpl extends TmfClientBaseImpl
    <BillingAccountCreate, BillingAccountUpdate, BillingAccount>
    implements BillingAccountClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<BillingAccount> getType() {
    return BillingAccount.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return BillingAccountClientException.class;
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
