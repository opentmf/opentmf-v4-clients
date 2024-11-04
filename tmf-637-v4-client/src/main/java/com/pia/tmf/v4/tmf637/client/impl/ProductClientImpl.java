package com.pia.tmf.v4.tmf637.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.product.model.Product;
import com.pia.tmf.v4.product.model.ProductCreate;
import com.pia.tmf.v4.product.model.ProductUpdate;
import com.pia.tmf.v4.tmf637.client.api.ProductClient;
import com.pia.tmf.v4.tmf637.exception.ProductClientException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ProductClientImpl extends TmfClientBaseImpl
    <ProductCreate, ProductUpdate, Product>
    implements ProductClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Product> getType() {
    return Product.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ProductClientException.class;
  }
}
