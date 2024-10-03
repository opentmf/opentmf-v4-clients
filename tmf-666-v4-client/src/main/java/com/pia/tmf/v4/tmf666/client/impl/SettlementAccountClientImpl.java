package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.SettlementAccountClient;
import com.pia.tmf.v4.tmf666.exception.SettlementAccountClientException;
import com.pia.tmf.v4.tmf666.model.SettlementAccount;
import com.pia.tmf.v4.tmf666.model.SettlementAccountCreate;
import com.pia.tmf.v4.tmf666.model.SettlementAccountUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class SettlementAccountClientImpl extends TmfClientBaseImpl
    <SettlementAccountCreate, SettlementAccountUpdate, SettlementAccount>
    implements SettlementAccountClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<SettlementAccount> getType() {
    return SettlementAccount.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return SettlementAccountClientException.class;
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
