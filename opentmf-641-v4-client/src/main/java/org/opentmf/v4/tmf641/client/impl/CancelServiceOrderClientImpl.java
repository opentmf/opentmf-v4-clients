package org.opentmf.v4.tmf641.client.impl;

import com.github.fge.jsonpatch.JsonPatch;
import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf641.client.api.CancelServiceOrderClient;
import org.opentmf.v4.tmf641.exception.CancelServiceOrderClientException;
import org.opentmf.v4.tmf641.model.CancelServiceOrder;
import org.opentmf.v4.tmf641.model.CancelServiceOrderCreate;
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
public class CancelServiceOrderClientImpl extends TmfClientBaseImpl
    <CancelServiceOrderCreate, CancelServiceOrderCreate, CancelServiceOrder>
    implements CancelServiceOrderClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<CancelServiceOrder> getType() {
    return CancelServiceOrder.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CancelServiceOrderClientException.class;
  }

  @Override
  public Mono<CancelServiceOrder> patch(String id, CancelServiceOrderCreate obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, CancelServiceOrderCreate obj, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelServiceOrder> patch(String id, JsonPatch jsonPatch) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, JsonPatch jsonPatch, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelServiceOrder> patchWithToken(String token, String id,
      CancelServiceOrderCreate obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, CancelServiceOrderCreate obj,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<CancelServiceOrder> patchWithToken(String token, String id, JsonPatch jsonPatch) {
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
