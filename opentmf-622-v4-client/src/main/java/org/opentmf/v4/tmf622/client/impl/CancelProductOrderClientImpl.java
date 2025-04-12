package org.opentmf.v4.tmf622.client.impl;

import com.github.fge.jsonpatch.JsonPatch;
import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf622.client.api.CancelProductOrderClient;
import org.opentmf.v4.tmf622.exception.CancelProductOrderClientException;
import org.opentmf.v4.tmf622.model.CancelProductOrder;
import org.opentmf.v4.tmf622.model.CancelProductOrderCreate;
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
