package com.pia.tmf.v4.tmf663.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf663.client.api.ShoppingCartClient;
import com.pia.tmf.v4.tmf663.exception.ShoppingCartClientException;
import com.pia.tmf.v4.tmf663.model.ShoppingCart;
import com.pia.tmf.v4.tmf663.model.ShoppingCartCreate;
import com.pia.tmf.v4.tmf663.model.ShoppingCartUpdate;
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
