package com.pia.tmf.v4.tmf632.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf632.client.api.IndividualClient;
import com.pia.tmf.v4.tmf632.exception.IndividualClientException;
import com.pia.tmf.v4.tmf632.model.Individual;
import com.pia.tmf.v4.tmf632.model.IndividualCreate;
import com.pia.tmf.v4.tmf632.model.IndividualUpdate;
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
