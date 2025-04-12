package org.opentmf.v4.tmf637.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.product.model.Product;
import org.opentmf.v4.product.model.ProductCreate;
import org.opentmf.v4.product.model.ProductUpdate;
import org.opentmf.v4.tmf637.client.api.ProductClient;
import org.opentmf.v4.tmf637.exception.ProductClientException;
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
