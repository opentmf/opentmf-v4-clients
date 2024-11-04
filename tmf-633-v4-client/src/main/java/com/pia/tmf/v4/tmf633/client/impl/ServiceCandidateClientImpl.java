package com.pia.tmf.v4.tmf633.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf633.client.api.ServiceCandidateClient;
import com.pia.tmf.v4.tmf633.exception.ServiceCandidateClientException;
import com.pia.tmf.v4.tmf633.model.ServiceCandidate;
import com.pia.tmf.v4.tmf633.model.ServiceCandidateCreate;
import com.pia.tmf.v4.tmf633.model.ServiceCandidateUpdate;
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
