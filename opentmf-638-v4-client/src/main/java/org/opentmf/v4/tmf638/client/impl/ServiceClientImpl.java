package org.opentmf.v4.tmf638.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.service.model.Service;
import org.opentmf.v4.service.model.ServiceCreate;
import org.opentmf.v4.service.model.ServiceUpdate;
import org.opentmf.v4.tmf638.client.api.ServiceClient;
import org.opentmf.v4.tmf638.exception.ServiceClientException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Cezmi Aslan
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ServiceClientImpl extends TmfClientBaseImpl
    <ServiceCreate, ServiceUpdate, Service>
    implements ServiceClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Service> getType() {
    return Service.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceClientException.class;
  }
}
