package org.opentmf.v4.tmf666.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf666.client.api.SettlementAccountClient;
import org.opentmf.v4.tmf666.exception.SettlementAccountClientException;
import org.opentmf.v4.tmf666.model.SettlementAccount;
import org.opentmf.v4.tmf666.model.SettlementAccountCreate;
import org.opentmf.v4.tmf666.model.SettlementAccountUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class SettlementAccountClientImpl extends TmfClientBaseImpl
    <SettlementAccountCreate, SettlementAccountUpdate, SettlementAccount>
    implements SettlementAccountClient {

  private final TmfClientConfig clientConfig;
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
}
