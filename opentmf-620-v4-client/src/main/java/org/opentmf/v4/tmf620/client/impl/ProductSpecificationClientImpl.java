package org.opentmf.v4.tmf620.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf620.client.api.ProductSpecificationClient;
import org.opentmf.v4.tmf620.exception.ProductSpecificationClientException;
import org.opentmf.v4.tmf620.model.ProductSpecification;
import org.opentmf.v4.tmf620.model.ProductSpecificationCreate;
import org.opentmf.v4.tmf620.model.ProductSpecificationUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ProductSpecificationClientImpl extends TmfClientBaseImpl
    <ProductSpecificationCreate, ProductSpecificationUpdate, ProductSpecification>
    implements ProductSpecificationClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ProductSpecification> getType() {
    return ProductSpecification.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ProductSpecificationClientException.class;
  }
}
