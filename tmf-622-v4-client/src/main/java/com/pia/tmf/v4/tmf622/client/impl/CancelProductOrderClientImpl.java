package com.pia.tmf.v4.tmf622.client.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf622.client.api.CancelProductOrderClient;
import com.pia.tmf.v4.tmf622.exception.CancelProductOrderClientException;
import com.pia.tmf.v4.tmf622.model.CancelProductOrder;
import com.pia.tmf.v4.tmf622.model.CancelProductOrderCreate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class CancelProductOrderClientImpl extends TmfClientBaseImpl
    <CancelProductOrderCreate, CancelProductOrderCreate, CancelProductOrder>
    implements CancelProductOrderClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<CancelProductOrder> getType() {
    return CancelProductOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CancelProductOrderClientException.class;
  }
  @Override
  public Mono<CancelProductOrder> patch(String id, CancelProductOrderCreate obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, CancelProductOrderCreate obj, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelProductOrder> patch(String id, JsonPatch jsonPatch) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, JsonPatch jsonPatch, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelProductOrder> patchWithToken(String token, String id,
      CancelProductOrderCreate obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, CancelProductOrderCreate obj,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelProductOrder> patchWithToken(String token, String id, JsonPatch jsonPatch) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, JsonPatch jsonPatch, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<Void> delete(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<Void> deleteWithToken(String token, String id) {
    throw new UnsupportedOperationException();
  }
}
