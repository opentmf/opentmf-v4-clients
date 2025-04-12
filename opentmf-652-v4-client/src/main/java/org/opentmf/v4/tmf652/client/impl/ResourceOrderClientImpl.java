package org.opentmf.v4.tmf652.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf652.client.api.ResourceOrderClient;
import org.opentmf.v4.tmf652.exception.ResourceOrderClientException;
import org.opentmf.v4.tmf652.model.ResourceOrder;
import org.opentmf.v4.tmf652.model.ResourceOrderCreate;
import org.opentmf.v4.tmf652.model.ResourceOrderUpdate;
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
