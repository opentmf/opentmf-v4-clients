package org.opentmf.v4.tmf633.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf633.client.api.ServiceCatalogClient;
import org.opentmf.v4.tmf633.exception.ServiceCatalogClientException;
import org.opentmf.v4.tmf633.model.ServiceCatalog;
import org.opentmf.v4.tmf633.model.ServiceCatalogCreate;
import org.opentmf.v4.tmf633.model.ServiceCatalogUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ServiceCatalogClientImpl extends TmfClientBaseImpl
    <ServiceCatalogCreate, ServiceCatalogUpdate, ServiceCatalog>
    implements ServiceCatalogClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceCatalog> getType() {
    return ServiceCatalog.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceCatalogClientException.class;
  }
}
