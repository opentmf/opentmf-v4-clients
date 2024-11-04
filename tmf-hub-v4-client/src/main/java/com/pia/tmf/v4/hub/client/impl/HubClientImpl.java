package com.pia.tmf.v4.hub.client.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.RetrievalContext;
import com.pia.tmf.common.model.Scope;
import com.pia.tmf.common.model.TmfPage;
import com.pia.tmf.common.util.TmfClientCommonHeaderUtil;
import com.pia.tmf.common.util.TmfClientCommonUtil;
import com.pia.tmf.v4.common.model.EventSubscriptionInput;
import com.pia.tmf.v4.hub.client.api.HubClient;
import com.pia.tmf.v4.hub.exception.HubClientException;
import com.pia.tmf.v4.hub.model.ExtendedEventSubscription;
import java.net.URI;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
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
  public Mono<ExtendedEventSubscription> get(String id, RetrievalContext retrievalContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> get(String id, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> get(String id, RetrievalContext retrievalContext, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> getWithToken(String token, String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> getWithToken(String token, String id, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> getWithToken(
      String token, String id, RetrievalContext retrievalContext) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> Mono<T> getWithToken(
      String token, String id, RetrievalContext retrievalContext, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> list() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> list(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> list(MultiValueMap<String, String> param) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> list(
      MultiValueMap<String, String> param, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listWithToken(String token) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listWithToken(String token, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listWithToken(
      String token, MultiValueMap<String, String> param) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listWithToken(
      String token, MultiValueMap<String, String> param, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAll(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAll(MultiValueMap<String, String> param) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAll(
      MultiValueMap<String, String> param, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAllWithToken(String token) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAllWithToken(String token, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAllWithToken(
      String token, MultiValueMap<String, String> param) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flux<ExtendedEventSubscription> listAllWithToken(
      String token, MultiValueMap<String, String> param, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPaged() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPaged(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPaged(
      MultiValueMap<String, String> param) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPaged(
      MultiValueMap<String, String> param, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPagedWithToken(String token) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPagedWithToken(
      String token, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPagedWithToken(
      String token, MultiValueMap<String, String> param) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<TmfPage<Flux<ExtendedEventSubscription>>> listPagedWithToken(
      String token, MultiValueMap<String, String> param, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(String id, EventSubscriptionInput obj) {
    return patch(id, obj, getType());
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(
      String id, EventSubscriptionInput obj, RetrievalContext retrievalContext) {
    return patch(id, obj, retrievalContext, getType());
  }

  @Override
  public <T> Mono<T> patch(String id, EventSubscriptionInput obj, Class<T> type) {
    return patch(id, obj, null, type);
  }

  @Override
  public <T> Mono<T> patch(
      String id, EventSubscriptionInput obj, RetrievalContext retrievalContext, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(
      String token, String id, EventSubscriptionInput obj) {
    return patchWithToken(token, id, obj, getType());
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(
      String token, String id, EventSubscriptionInput obj, RetrievalContext retrievalContext) {
    return patchWithToken(token, id, obj, retrievalContext, getType());
  }

  @Override
  public <T> Mono<T> patchWithToken(
      String token, String id, EventSubscriptionInput obj, Class<T> type) {
    return patchWithToken(token, id, obj, null, type);
  }

  @Override
  public <T> Mono<T> patchWithToken(
      String token,
      String id,
      EventSubscriptionInput obj,
      RetrievalContext retrievalContext,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(String id, JsonPatch jsonPatch) {
    return patch(id, jsonPatch, getType());
  }

  @Override
  public Mono<ExtendedEventSubscription> patch(
      String id, JsonPatch jsonPatch, RetrievalContext retrievalContext) {
    return patch(id, jsonPatch, retrievalContext, getType());
  }

  @Override
  public <T> Mono<T> patch(String id, JsonPatch jsonPatch, Class<T> type) {
    return patch(id, jsonPatch, null, type);
  }

  @Override
  public <T> Mono<T> patch(
      String id, JsonPatch jsonPatch, RetrievalContext retrievalContext, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(
      String token, String id, JsonPatch jsonPatch) {
    return patchWithToken(token, id, jsonPatch, getType());
  }

  @Override
  public Mono<ExtendedEventSubscription> patchWithToken(
      String token, String id, JsonPatch jsonPatch, RetrievalContext retrievalContext) {
    return patchWithToken(token, id, jsonPatch, retrievalContext, getType());
  }

  @Override
  public <T> Mono<T> patchWithToken(String token, String id, JsonPatch jsonPatch, Class<T> type) {
    return patchWithToken(token, id, jsonPatch, null, type);
  }

  @Override
  public <T> Mono<T> patchWithToken(
      String token,
      String id,
      JsonPatch jsonPatch,
      RetrievalContext retrievalContext,
      Class<T> type) {
    throw new UnsupportedOperationException();
  }
}