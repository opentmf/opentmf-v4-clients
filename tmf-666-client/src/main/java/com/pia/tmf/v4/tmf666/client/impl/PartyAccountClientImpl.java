package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.PartyAccountClient;
import com.pia.tmf.v4.tmf666.exception.PartyAccountClientException;
import com.pia.tmf.v4.tmf666.model.PartyAccount;
import com.pia.tmf.v4.tmf666.model.PartyAccountCreate;
import com.pia.tmf.v4.tmf666.model.PartyAccountUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class PartyAccountClientImpl extends TmfClientBaseImpl
    <PartyAccountCreate, PartyAccountUpdate, PartyAccount>
    implements PartyAccountClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<PartyAccount> getType() {
    return PartyAccount.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return PartyAccountClientException.class;
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
