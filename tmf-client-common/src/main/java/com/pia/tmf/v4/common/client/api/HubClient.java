package com.pia.tmf.v4.common.client.api;

import com.pia.tmf.v4.common.model.EventSubscriptionInput;
import com.pia.tmf.v4.common.model.ExtendedEventSubscription;
import reactor.core.publisher.Mono;

/**
 * @author Gokhan Demir
 * @see EventSubscriptionInput
 * @see ExtendedEventSubscription
 */
public interface HubClient extends TmfClient
    <EventSubscriptionInput, EventSubscriptionInput, ExtendedEventSubscription> {

  Mono<ExtendedEventSubscription> registerListener(EventSubscriptionInput input);
  Mono<ExtendedEventSubscription> registerListener(String token, EventSubscriptionInput input);

  Mono<Void> unregisterListener(String id);
  Mono<Void> unregisterListener(String token, String id);

  /**
   * Unregisters from the previously registered server.
   * This method is useful if the hub endpoint is changed, to unregister from
   * the previously registered one.
   * @param eventSubscription The previous registration information, which includes the hubUri.
   * @return a mono void.
   */
  Mono<Void> unregisterListener(ExtendedEventSubscription eventSubscription);
}
