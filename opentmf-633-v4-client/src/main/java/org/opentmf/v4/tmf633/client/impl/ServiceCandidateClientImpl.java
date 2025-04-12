package org.opentmf.v4.tmf633.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf633.client.api.ServiceCandidateClient;
import org.opentmf.v4.tmf633.exception.ServiceCandidateClientException;
import org.opentmf.v4.tmf633.model.ServiceCandidate;
import org.opentmf.v4.tmf633.model.ServiceCandidateCreate;
import org.opentmf.v4.tmf633.model.ServiceCandidateUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ServiceCandidateClientImpl extends TmfClientBaseImpl
    <ServiceCandidateCreate, ServiceCandidateUpdate, ServiceCandidate>
    implements ServiceCandidateClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceCandidate> getType() {
    return ServiceCandidate.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceCandidateClientException.class;
  }
}
