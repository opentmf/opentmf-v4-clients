package org.opentmf.v4.tmf634.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf634.client.api.ResourceCategoryClient;
import org.opentmf.v4.tmf634.exception.ResourceCategoryClientException;
import org.opentmf.v4.tmf634.model.ResourceCategory;
import org.opentmf.v4.tmf634.model.ResourceCategoryCreate;
import org.opentmf.v4.tmf634.model.ResourceCategoryUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ResourceCategoryClientImpl extends TmfClientBaseImpl
    <ResourceCategoryCreate, ResourceCategoryUpdate, ResourceCategory>
    implements ResourceCategoryClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceCategory> getType() {
    return ResourceCategory.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceCategoryClientException.class;
  }
}
