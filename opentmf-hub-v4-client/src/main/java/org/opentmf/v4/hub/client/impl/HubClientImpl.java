package org.opentmf.v4.hub.client.impl;

import com.github.fge.jsonpatch.JsonPatch;
import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.Scope;
import org.opentmf.common.model.TmfPage;
import org.opentmf.common.model.TmfRequestContext;
import org.opentmf.common.util.TmfClientCommonHeaderUtil;
import org.opentmf.common.util.TmfClientCommonUtil;
import org.opentmf.v4.common.model.EventSubscriptionInput;
import org.opentmf.v4.hub.client.api.HubClient;
import org.opentmf.v4.hub.exception.HubClientException;
import org.opentmf.v4.hub.model.ExtendedEventSubscription;
import java.net.URI;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public final class HubClientImpl
    extends TmfClientBaseImpl
    <EventSubscriptionInput, EventSubscriptionInput, ExtendedEventSubscription>
    implements HubClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ExtendedEventSubscription> getType() {
    return ExtendedEventSubscription.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return HubClientException.class;
  }


  @Override
  public Mono<ExtendedEventSubscription> registerListener(EventSubscriptionInput input) {
    return getToken(Scope.POST).flatMap(token -> registerListener(token, input));
  }

  @Override
  public Mono<ExtendedEventSubscription> registerListener(
      String token, EventSubscriptionInput input) {
    var url =
        clientConfig.getBaseUrl()
            + (clientConfig.getContextPath() == null ? "" : clientConfig.getContextPath())
            + clientConfig.getEndpoint();
    return postWithToken(token, input, getType())
        .doOnNext(
            extendedEventSubscription -> extendedEventSubscription.setHubUri(URI.create(url)));
  }

  @Override
  public Mono<Void> unregisterListener(String id) {
    return delete(id);
  }

  @Override
  public Mono<Void> unregisterListener(String token, String id) {
    return deleteWithToken(token, id);
  }

  @Override
  public Mono<Void> unregisterListener(ExtendedEventSubscription eventSubscription) {
    var uri =
        UriComponentsBuilder.fromUri(eventSubscription.getHubUri())
            .pathSegment("{id}")
            .build(eventSubscription.getId());
    return getToken(Scope.DELETE)
        .flatMap(
            token ->
                TmfClientCommonUtil.deleteRequest(
                    getWebClient(),
                    uri,
                    TmfClientCommonHeaderUtil.prepareHeaderConsumer(token, getTokenService()),
                    this::handleError,
                    getClientProperties()));
  }

  @Override
  public Mono<ExtendedEventSubscription> get(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> get(String id, TmfRequestContext requestContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> get(String id, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> get(String id, TmfRequestContext requestContext, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> getWithToken(String token, String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> getWithToken(String token, String id,
      TmfRequestContext requestContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> getWithToken(String token, String id, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> getWithToken(String token, String id, TmfRequestContext requestContext,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> list() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> list(Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> list(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> list(Pageable request, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listWithToken(String token) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> listWithToken(String token, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listWithToken(String token, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> listWithToken(String token, Pageable request, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> listAll(Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAll(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> listAll(Pageable pageable, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAllWithToken(String token) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> listAllWithToken(String token, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAllWithToken(String token, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Flux<T> listAllWithToken(String token, Pageable pageable, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPaged() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<TmfPage<Flux<T>>> listPaged(Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPaged(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<TmfPage<Flux<T>>> listPaged(Pageable pageable, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPagedWithToken(String token) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<TmfPage<Flux<T>>> listPagedWithToken(String token, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPagedWithToken(String token,
      Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<TmfPage<Flux<T>>> listPagedWithToken(String token, Pageable pageable,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(String id, EventSubscriptionInput obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(String id, EventSubscriptionInput obj,
      TmfRequestContext requestContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, EventSubscriptionInput obj, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, EventSubscriptionInput obj, TmfRequestContext requestContext,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(String id, JsonPatch jsonPatch) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(String id, JsonPatch jsonPatch,
      TmfRequestContext requestContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, JsonPatch jsonPatch, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patch(String id, JsonPatch jsonPatch, TmfRequestContext requestContext,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(String token, String id,
      EventSubscriptionInput obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(String token, String id,
      EventSubscriptionInput obj, TmfRequestContext requestContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, EventSubscriptionInput obj,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, EventSubscriptionInput obj,
      TmfRequestContext requestContext, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(String token, String id,
      JsonPatch jsonPatch) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(String token, String id,
      JsonPatch jsonPatch, TmfRequestContext requestContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, JsonPatch jsonPatch, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, JsonPatch jsonPatch,
      TmfRequestContext requestContext, Class<T> type) {
    throw new UnsupportedOperationException();
  }
}