package com.pia.tmf.v4.tmf633.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf633.client.api.ServiceCatalogClient;
import com.pia.tmf.v4.tmf633.exception.ServiceCatalogClientException;
import com.pia.tmf.v4.tmf633.model.ServiceCatalog;
import com.pia.tmf.v4.tmf633.model.ServiceCatalogCreate;
import com.pia.tmf.v4.tmf633.model.ServiceCatalogUpdate;
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
