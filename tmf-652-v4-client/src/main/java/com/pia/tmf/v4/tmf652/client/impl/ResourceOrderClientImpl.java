package com.pia.tmf.v4.tmf652.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf652.client.api.ResourceOrderClient;
import com.pia.tmf.v4.tmf652.exception.ResourceOrderClientException;
import com.pia.tmf.v4.tmf652.model.ResourceOrder;
import com.pia.tmf.v4.tmf652.model.ResourceOrderCreate;
import com.pia.tmf.v4.tmf652.model.ResourceOrderUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ResourceOrderClientImpl extends TmfClientBaseImpl
    <ResourceOrderCreate, ResourceOrderUpdate, ResourceOrder>
    implements ResourceOrderClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceOrder> getType() {
    return ResourceOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceOrderClientException.class;
  }
}
