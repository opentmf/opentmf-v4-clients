package org.opentmf.v4.tmf666.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf666.client.api.FinancialAccountClient;
import org.opentmf.v4.tmf666.exception.FinancialAccountClientException;
import org.opentmf.v4.tmf666.model.FinancialAccount;
import org.opentmf.v4.tmf666.model.FinancialAccountCreate;
import org.opentmf.v4.tmf666.model.FinancialAccountUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class FinancialAccountClientImpl extends TmfClientBaseImpl
    <FinancialAccountCreate, FinancialAccountUpdate, FinancialAccount>
    implements FinancialAccountClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<FinancialAccount> getType() {
    return FinancialAccount.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return FinancialAccountClientException.class;
  }
}
