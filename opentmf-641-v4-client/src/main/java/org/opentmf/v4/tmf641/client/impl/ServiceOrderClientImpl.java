package org.opentmf.v4.tmf641.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf641.client.api.ServiceOrderClient;
import org.opentmf.v4.tmf641.exception.ServiceOrderClientException;
import org.opentmf.v4.tmf641.model.ServiceOrder;
import org.opentmf.v4.tmf641.model.ServiceOrderCreate;
import org.opentmf.v4.tmf641.model.ServiceOrderUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ServiceOrderClientImpl extends TmfClientBaseImpl
    <ServiceOrderCreate, ServiceOrderUpdate, ServiceOrder>
    implements ServiceOrderClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceOrder> getType() {
    return ServiceOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceOrderClientException.class;
  }
}
