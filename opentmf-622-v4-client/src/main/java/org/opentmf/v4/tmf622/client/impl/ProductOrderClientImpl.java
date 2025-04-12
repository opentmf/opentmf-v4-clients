package org.opentmf.v4.tmf622.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf622.client.api.ProductOrderClient;
import org.opentmf.v4.tmf622.exception.ProductOrderClientException;
import org.opentmf.v4.tmf622.model.ProductOrder;
import org.opentmf.v4.tmf622.model.ProductOrderCreate;
import org.opentmf.v4.tmf622.model.ProductOrderUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ProductOrderClientImpl extends TmfClientBaseImpl
    <ProductOrderCreate, ProductOrderUpdate, ProductOrder>
    implements ProductOrderClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ProductOrder> getType() {
    return ProductOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ProductOrderClientException.class;
  }
}
