package org.opentmf.v4.tmf632.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf632.client.api.IndividualClient;
import org.opentmf.v4.tmf632.exception.IndividualClientException;
import org.opentmf.v4.tmf632.model.Individual;
import org.opentmf.v4.tmf632.model.IndividualCreate;
import org.opentmf.v4.tmf632.model.IndividualUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class IndividualClientImpl extends TmfClientBaseImpl
    <IndividualCreate, IndividualUpdate, Individual>
    implements IndividualClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Individual> getType() {
    return Individual.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return IndividualClientException.class;
  }
}
