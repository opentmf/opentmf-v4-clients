package com.pia.tmf.v4.tmf639.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.resource.model.Resource;
import com.pia.tmf.v4.resource.model.ResourceCreate;
import com.pia.tmf.v4.resource.model.ResourceUpdate;
import com.pia.tmf.v4.tmf639.client.api.ResourceClient;
import com.pia.tmf.v4.tmf639.exception.ResourceClientException;
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
