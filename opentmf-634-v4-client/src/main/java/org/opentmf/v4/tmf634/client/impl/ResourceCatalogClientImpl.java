package org.opentmf.v4.tmf634.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf634.client.api.ResourceCatalogClient;
import org.opentmf.v4.tmf634.exception.ResourceCatalogClientException;
import org.opentmf.v4.tmf634.model.ResourceCatalog;
import org.opentmf.v4.tmf634.model.ResourceCatalogCreate;
import org.opentmf.v4.tmf634.model.ResourceCatalogUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ResourceCatalogClientImpl extends TmfClientBaseImpl
    <ResourceCatalogCreate, ResourceCatalogUpdate, ResourceCatalog>
    implements ResourceCatalogClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceCatalog> getType() {
    return ResourceCatalog.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceCatalogClientException.class;
  }
}
