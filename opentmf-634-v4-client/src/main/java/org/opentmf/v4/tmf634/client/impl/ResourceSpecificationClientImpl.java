package org.opentmf.v4.tmf634.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf634.client.api.ResourceSpecificationClient;
import org.opentmf.v4.tmf634.exception.ResourceSpecificationClientException;
import org.opentmf.v4.tmf634.model.ResourceSpecification;
import org.opentmf.v4.tmf634.model.ResourceSpecificationCreate;
import org.opentmf.v4.tmf634.model.ResourceSpecificationUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ResourceSpecificationClientImpl extends TmfClientBaseImpl
    <ResourceSpecificationCreate, ResourceSpecificationUpdate, ResourceSpecification>
    implements ResourceSpecificationClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceSpecification> getType() {
    return ResourceSpecification.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceSpecificationClientException.class;
  }
}
