package com.pia.tmf.v4.tmf634.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf634.client.api.ResourceCatalogClient;
import com.pia.tmf.v4.tmf634.exception.ResourceCatalogClientException;
import com.pia.tmf.v4.tmf634.model.ResourceCatalog;
import com.pia.tmf.v4.tmf634.model.ResourceCatalogCreate;
import com.pia.tmf.v4.tmf634.model.ResourceCatalogUpdate;
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
