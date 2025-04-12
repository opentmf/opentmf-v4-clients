package org.opentmf.v4.tmf620.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf620.client.api.ProductOfferingPriceClient;
import org.opentmf.v4.tmf620.exception.ProductOfferingPriceClientException;
import org.opentmf.v4.tmf620.model.ProductOfferingPrice;
import org.opentmf.v4.tmf620.model.ProductOfferingPriceCreate;
import org.opentmf.v4.tmf620.model.ProductOfferingPriceUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ProductOfferingPriceClientImpl extends TmfClientBaseImpl
    <ProductOfferingPriceCreate, ProductOfferingPriceUpdate, ProductOfferingPrice>
    implements ProductOfferingPriceClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ProductOfferingPrice> getType() {
    return ProductOfferingPrice.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ProductOfferingPriceClientException.class;
  }
}
