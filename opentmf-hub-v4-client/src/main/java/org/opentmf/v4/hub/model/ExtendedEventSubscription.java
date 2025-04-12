package org.opentmf.v4.hub.model;

import org.opentmf.v4.common.model.EventSubscription;
import java.net.URI;
import lombok.Getter;
import lombok.Setter;

/**
 * Enhances EventSubscription by adding hubUri, which is the URL that was used during the
 * registration.
 *
 * @author Gokhan Demir
 */
@Getter
@Setter
public class ExtendedEventSubscription extends EventSubscription {

  private URI hubUri;

  @Override
  public String toString() {
    return "ExtendedEventSubscription{" +
        "hubUri=" + hubUri +
        ", listenerId=" + getId() +
        ", callbackUri=" + getCallback() +
        ", query=" + getQuery() +
        "}";
  }
}