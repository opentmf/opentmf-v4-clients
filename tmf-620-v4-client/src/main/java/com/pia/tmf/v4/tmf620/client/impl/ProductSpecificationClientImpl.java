package com.pia.tmf.v4.tmf620.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf620.client.api.ProductSpecificationClient;
import com.pia.tmf.v4.tmf620.exception.ProductSpecificationClientException;
import com.pia.tmf.v4.tmf620.model.ProductSpecification;
import com.pia.tmf.v4.tmf620.model.ProductSpecificationCreate;
import com.pia.tmf.v4.tmf620.model.ProductSpecificationUpdate;
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
