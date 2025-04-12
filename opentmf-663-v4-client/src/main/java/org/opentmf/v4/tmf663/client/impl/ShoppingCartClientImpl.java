package org.opentmf.v4.tmf663.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf663.client.api.ShoppingCartClient;
import org.opentmf.v4.tmf663.exception.ShoppingCartClientException;
import org.opentmf.v4.tmf663.model.ShoppingCart;
import org.opentmf.v4.tmf663.model.ShoppingCartCreate;
import org.opentmf.v4.tmf663.model.ShoppingCartUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ShoppingCartClientImpl extends TmfClientBaseImpl
    <ShoppingCartCreate, ShoppingCartUpdate, ShoppingCart>
    implements ShoppingCartClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ShoppingCart> getType() {
    return ShoppingCart.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ShoppingCartClientException.class;
  }
}
