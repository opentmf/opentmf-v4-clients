package org.opentmf.v4.tmf652.client.impl;

import com.github.fge.jsonpatch.JsonPatch;
import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf652.client.api.CancelResourceOrderClient;
import org.opentmf.v4.tmf652.exception.CancelResourceOrderClientException;
import org.opentmf.v4.tmf652.model.CancelResourceOrder;
import org.opentmf.v4.tmf652.model.CancelResourceOrderCreate;
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
public class CancelResourceOrderClientImpl extends TmfClientBaseImpl
    <CancelResourceOrderCreate, CancelResourceOrderCreate, CancelResourceOrder>
    implements CancelResourceOrderClient {

  private final TmfClientConfig clientConfig;
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
