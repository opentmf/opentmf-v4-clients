package org.opentmf.v4.tmf633.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf633.client.api.ServiceSpecificationClient;
import org.opentmf.v4.tmf633.exception.ServiceSpecificationClientException;
import org.opentmf.v4.tmf633.model.ServiceSpecification;
import org.opentmf.v4.tmf633.model.ServiceSpecificationCreate;
import org.opentmf.v4.tmf633.model.ServiceSpecificationUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ServiceSpecificationClientImpl extends TmfClientBaseImpl
    <ServiceSpecificationCreate, ServiceSpecificationUpdate, ServiceSpecification>
    implements ServiceSpecificationClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceSpecification> getType() {
    return ServiceSpecification.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceSpecificationClientException.class;
  }
}
