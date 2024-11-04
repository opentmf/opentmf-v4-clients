package com.pia.tmf.v4.tmf641.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf641.client.api.ServiceOrderClient;
import com.pia.tmf.v4.tmf641.exception.ServiceOrderClientException;
import com.pia.tmf.v4.tmf641.model.ServiceOrder;
import com.pia.tmf.v4.tmf641.model.ServiceOrderCreate;
import com.pia.tmf.v4.tmf641.model.ServiceOrderUpdate;
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
