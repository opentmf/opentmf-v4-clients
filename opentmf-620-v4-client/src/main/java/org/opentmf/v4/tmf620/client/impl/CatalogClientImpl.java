package org.opentmf.v4.tmf620.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf620.client.api.CatalogClient;
import org.opentmf.v4.tmf620.exception.CatalogClientException;
import org.opentmf.v4.tmf620.model.Catalog;
import org.opentmf.v4.tmf620.model.CatalogCreate;
import org.opentmf.v4.tmf620.model.CatalogUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class CatalogClientImpl extends TmfClientBaseImpl
    <CatalogCreate, CatalogUpdate, Catalog>
    implements CatalogClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Catalog> getType() {
    return Catalog.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CatalogClientException.class;
  }
}
