package com.pia.tmf.v4.tmf669.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf669.client.api.PartyRoleClient;
import com.pia.tmf.v4.tmf669.exception.PartyRoleClientException;
import com.pia.tmf.v4.tmf669.model.PartyRole;
import com.pia.tmf.v4.tmf669.model.PartyRoleCreate;
import com.pia.tmf.v4.tmf669.model.PartyRoleUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class PartyRoleClientImpl extends TmfClientBaseImpl
    <PartyRoleCreate, PartyRoleUpdate, PartyRole>
    implements PartyRoleClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<PartyRole> getType() {
    return PartyRole.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return PartyRoleClientException.class;
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
