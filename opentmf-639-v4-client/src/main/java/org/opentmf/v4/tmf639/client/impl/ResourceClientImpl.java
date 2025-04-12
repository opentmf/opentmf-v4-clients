package org.opentmf.v4.tmf639.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.resource.model.Resource;
import org.opentmf.v4.resource.model.ResourceCreate;
import org.opentmf.v4.resource.model.ResourceUpdate;
import org.opentmf.v4.tmf639.client.api.ResourceClient;
import org.opentmf.v4.tmf639.exception.ResourceClientException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ResourceClientImpl extends TmfClientBaseImpl
    <ResourceCreate, ResourceUpdate, Resource>
    implements ResourceClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Resource> getType() {
    return Resource.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceClientException.class;
  }
}
