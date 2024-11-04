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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class PartyRoleClientImpl extends TmfClientBaseImpl
    <PartyRoleCreate, PartyRoleUpdate, PartyRole>
    implements PartyRoleClient {

  private final TmfClientConfig clientConfig;
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
}
