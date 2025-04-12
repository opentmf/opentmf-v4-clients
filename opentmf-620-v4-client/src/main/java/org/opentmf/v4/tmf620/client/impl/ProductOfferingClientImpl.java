package org.opentmf.v4.tmf620.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf620.client.api.ProductOfferingClient;
import org.opentmf.v4.tmf620.exception.ProductOfferingClientException;
import org.opentmf.v4.tmf620.model.ProductOffering;
import org.opentmf.v4.tmf620.model.ProductOfferingCreate;
import org.opentmf.v4.tmf620.model.ProductOfferingUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ProductOfferingClientImpl extends TmfClientBaseImpl
    <ProductOfferingCreate, ProductOfferingUpdate, ProductOffering>
    implements ProductOfferingClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ProductOffering> getType() {
    return ProductOffering.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ProductOfferingClientException.class;
  }
}
