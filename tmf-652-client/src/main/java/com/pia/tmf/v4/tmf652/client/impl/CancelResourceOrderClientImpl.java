package com.pia.tmf.v4.tmf652.client.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf652.client.api.CancelResourceOrderClient;
import com.pia.tmf.v4.tmf652.exception.CancelResourceOrderClientException;
import com.pia.tmf.v4.tmf652.model.CancelResourceOrder;
import com.pia.tmf.v4.tmf652.model.CancelResourceOrderCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CancelResourceOrderClientImpl extends TmfClientBaseImpl
    <CancelResourceOrderCreate, CancelResourceOrderCreate, CancelResourceOrder>
    implements CancelResourceOrderClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<CancelResourceOrder> getType() {
    return CancelResourceOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CancelResourceOrderClientException.class;
  }

  @Override
  protected TmfClientConfig getClientConfig() {
    return this.config;
  }

  @Override
  protected WebClient getWebClient() {
    return this.webClient;
  }

  @Override
  protected TokenService getTokenService() {
    return this.tokenService;
  }

  @Override
  protected BaseClientProperties getClientProperties() {
    return this.clientProperties;
  }

  @Override
  public Mono<CancelResourceOrder> patch(String id, CancelResourceOrderCreate obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, CancelResourceOrderCreate obj, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelResourceOrder> patch(String id, JsonPatch jsonPatch) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, JsonPatch jsonPatch, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelResourceOrder> patchWithToken(String token, String id,
      CancelResourceOrderCreate obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, CancelResourceOrderCreate obj,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelResourceOrder> patchWithToken(String token, String id, JsonPatch jsonPatch) {
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
