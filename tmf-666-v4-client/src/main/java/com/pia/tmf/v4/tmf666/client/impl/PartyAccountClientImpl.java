package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.PartyAccountClient;
import com.pia.tmf.v4.tmf666.exception.PartyAccountClientException;
import com.pia.tmf.v4.tmf666.model.PartyAccount;
import com.pia.tmf.v4.tmf666.model.PartyAccountCreate;
import com.pia.tmf.v4.tmf666.model.PartyAccountUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class PartyAccountClientImpl extends TmfClientBaseImpl
    <PartyAccountCreate, PartyAccountUpdate, PartyAccount>
    implements PartyAccountClient {

  private final TmfClientConfig clientConfig;
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
}
