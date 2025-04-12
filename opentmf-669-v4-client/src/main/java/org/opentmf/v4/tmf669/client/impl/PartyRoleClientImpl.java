package org.opentmf.v4.tmf669.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf669.client.api.PartyRoleClient;
import org.opentmf.v4.tmf669.exception.PartyRoleClientException;
import org.opentmf.v4.tmf669.model.PartyRole;
import org.opentmf.v4.tmf669.model.PartyRoleCreate;
import org.opentmf.v4.tmf669.model.PartyRoleUpdate;
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
