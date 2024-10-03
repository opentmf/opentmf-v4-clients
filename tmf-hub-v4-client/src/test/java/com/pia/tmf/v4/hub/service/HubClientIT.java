package com.pia.tmf.v4.hub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.pia.tmf.common.config.TmfClientConfigurations;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.model.RetrievalContext;
import com.pia.tmf.common.model.TmfOffsetRequest;
import com.pia.tmf.v4.common.model.EventSubscription;
import com.pia.tmf.v4.hub.client.api.HubClient;
import com.pia.tmf.v4.hub.config.HubClientProvider;
import com.pia.tmf.v4.hub.exception.HubClientException;
import com.pia.tmf.v4.hub.helper.MockServerUtils;
import com.pia.tmf.v4.hub.model.ExtendedEventSubscription;
import java.net.URI;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import reactor.test.StepVerifier;

/**
 * @author Gokhan Demir
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class HubClientIT {

  @Autowired private TmfClientConfigurations tmfClientConfigurations;
  @Autowired private HubClientProvider hubClientProvider;

  private HubClient hubClient;

  @BeforeAll
  void beforeAll() {
    TmfClientConfig config = tmfClientConfigurations.getTmfClients().get("sh-hub-client");
    config.setBaseUrl(MockServerUtils.BASE_URL);
    hubClient = hubClientProvider.getTmfClient(config, "sh");
  }

  @BeforeEach
  void beforeEach() {
    MockServerUtils.resetMockServer();
  }

  @Test
  void testRegisterListener_withValidData_returnsValidResult() {
    MockServerUtils.setUpDynamicPostCallback("/hub");
    var subscriptionInput = buildEventSubscriptionInput();
    StepVerifier.create(hubClient.registerListener(subscriptionInput))
        .assertNext(
            data -> {
              assertNotNull(data);
              assertNotNull(data.getId());
              assertNotNull(data.getHubUri());
              assertTrue(data.getHubUri().toString().contains("/hub"));
              assertEquals(subscriptionInput.getQuery(), data.getQuery());
              assertEquals(subscriptionInput.getCallback(), data.getCallback());
              assertTrue(
                  data.toString().contains("query=eventType=serviceCatalogStateChangeEvent}"));
            })
        .verifyComplete();
  }

  @Test
  void testRegisterListener_withEventSubscription_returnsValidResult() {
    MockServerUtils.setUpDynamicPostCallback("/hub");
    var subscriptionInput = buildBaseEventSubscriptionInput();
    StepVerifier.create(hubClient.registerListener(subscriptionInput))
        .assertNext(
            data -> {
              assertNotNull(data);
              assertNotNull(data.getId());
              assertNotNull(data.getHubUri());
              assertTrue(data.getHubUri().toString().contains("/hub"));
              assertEquals(subscriptionInput.getQuery(), data.getQuery());
              assertEquals(subscriptionInput.getCallback(), data.getCallback());
              assertTrue(
                  data.toString().contains("query=eventType=serviceCatalogStateChangeEvent}"));
            })
        .verifyComplete();
  }

  @Test
  void testRegisterListener_withContextPath_returnsValidResult() {
    TmfClientConfig config = tmfClientConfigurations.getTmfClients().get("sh-hub-client");
    config.setContextPath("/testContext");
    MockServerUtils.setUpDynamicPostCallback("/testContext/hub");
    var subscriptionInput = buildBaseEventSubscriptionInput();
    StepVerifier.create(hubClient.registerListener(subscriptionInput))
        .assertNext(
            data -> {
              assertNotNull(data);
              assertNotNull(data.getId());
              assertNotNull(data.getHubUri());
              assertTrue(data.getHubUri().toString().contains("/hub"));
              assertEquals(subscriptionInput.getQuery(), data.getQuery());
              assertEquals(subscriptionInput.getCallback(), data.getCallback());
              assertTrue(
                  data.toString().contains("query=eventType=serviceCatalogStateChangeEvent}"));
            })
        .verifyComplete();
    config.setContextPath(null);
  }

  @Test
  void testRegisterListener_withEmptyBodyResponse_returnsReturnProductOrderException() {
    MockServerUtils.setupPost("/hub", HttpStatus.BAD_REQUEST);

    StepVerifier.create(hubClient.registerListener(buildEventSubscriptionInput()))
        .expectErrorMatches(
            error ->
                (error instanceof HubClientException e)
                    && (e.getStatusCode() == HttpStatus.BAD_REQUEST))
        .verify();
  }

  @Test
  void testRegisterListener_withErrorResponse_returnsError() {
    MockServerUtils.setupPost("/hub", HttpStatus.BAD_REQUEST);

    StepVerifier.create(hubClient.registerListener(buildEventSubscriptionInput()))
        .expectErrorMatches(
            error ->
                (error instanceof HubClientException e)
                    && (e.getStatusCode() == HttpStatus.BAD_REQUEST))
        .verify();
  }

  @Test
  void testUnregisterListener_withValidData_returnsValidResult() {
    MockServerUtils.setUpExpectation(HttpMethod.DELETE.name(), "/hub/id", HttpStatus.NO_CONTENT);

    StepVerifier.create(hubClient.unregisterListener("id")).verifyComplete();
  }

  @Test
  void testUnregisterListener_withToken_returnsValidResult() {
    MockServerUtils.setUpExpectation(HttpMethod.DELETE.name(), "/hub/id", HttpStatus.NO_CONTENT);

    StepVerifier.create(hubClient.unregisterListener("token", "id")).verifyComplete();
  }

  @Test
  void test_tmfGet_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.get("id"));
  }

  @Test
  void test_tmfGetWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.get("id", RetrievalContext.builder().withFields("fields").build()));
  }

  @Test
  void test_tmfGetWithClassType_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.get("id", Object.class));
  }

  @Test
  void test_tmfGetWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.get(
                "id", RetrievalContext.builder().withFields("fields").build(), Object.class));
  }

  @Test
  void test_tmfGetWithToken_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.getWithToken("token", "id"));
  }

  @Test
  void test_tmfGetWithTokenWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.getWithToken(
                "token", "id", RetrievalContext.builder().withFields("fields").build()));
  }

  @Test
  void test_tmfGetWithTokenWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.getWithToken("token", "id", Object.class));
  }

  @Test
  void test_tmfGetWithTokenWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.getWithToken(
                "token",
                "id",
                RetrievalContext.builder().withFields("fields").build(),
                Object.class));
  }

  @Test
  void test_tmfList_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.list());
  }

  @Test
  void test_tmfListWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class, () -> hubClient.list(TmfOffsetRequest.of(0, 10)));
  }

  @Test
  void test_tmfListWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class, () -> hubClient.list(new LinkedMultiValueMap<>()));
  }

  @Test
  void test_tmfListWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.list(new LinkedMultiValueMap<>(), TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListWithToken_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.listWithToken("token"));
  }

  @Test
  void test_tmfListWithTokenWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listWithToken("token", TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListWithTokenWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listWithToken("token", new LinkedMultiValueMap<>()));
  }

  @Test
  void test_tmfListWithTokenWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listWithToken("token", new LinkedMultiValueMap<>(), TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListAll_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.listAll());
  }

  @Test
  void test_tmfListAllWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class, () -> hubClient.listAll(TmfOffsetRequest.of(0, 10)));
  }

  @Test
  void test_tmfListAllWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class, () -> hubClient.listAll(new LinkedMultiValueMap<>()));
  }

  @Test
  void test_tmfListAllWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listAll(new LinkedMultiValueMap<>(), TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListAllWithToken_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.listAllWithToken("token"));
  }

  @Test
  void test_tmfListAllWithTokenWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listAllWithToken("token", TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListAllWithTokenWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listAllWithToken("token", new LinkedMultiValueMap<>()));
  }

  @Test
  void test_tmfListAllWithTokenWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.listAllWithToken(
                "token", new LinkedMultiValueMap<>(), TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListPaged_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.listPaged());
  }

  @Test
  void test_tmfListPagedWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class, () -> hubClient.listPaged(TmfOffsetRequest.of(0, 10)));
  }

  @Test
  void test_tmfListPagedWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listPaged(new LinkedMultiValueMap<>()));
  }

  @Test
  void test_tmfListPagedWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listPaged(new LinkedMultiValueMap<>(), TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListPagedWithToken_throwUnsupportedException() {
    assertThrows(UnsupportedOperationException.class, () -> hubClient.listPagedWithToken("token"));
  }

  @Test
  void test_tmfListPagedWithTokenWithFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listPagedWithToken("token", TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfListPagedWithTokenWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.listPagedWithToken("token", new LinkedMultiValueMap<>()));
  }

  @Test
  void test_tmfListPagedWithTokenWithClassTypeAndFilter_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.listPagedWithToken(
                "token", new LinkedMultiValueMap<>(), TmfOffsetRequest.of()));
  }

  @Test
  void test_tmfPatch_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.patch("id", new ExtendedEventSubscription()));
  }

  @Test
  void test_tmfPatchWithRetrievalContext_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.patch(
                "id", new ExtendedEventSubscription(), RetrievalContext.builder().build()));
  }

  @Test
  void test_tmfPatchWithToken_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.patchWithToken("token", "id", new ExtendedEventSubscription()));
  }

  @Test
  void test_tmfPatchWithTokenWithRetrievalContext_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.patchWithToken(
                "token",
                "id",
                new ExtendedEventSubscription(),
                RetrievalContext.builder().build()));
  }

  @Test
  void test_tmfPatchWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.patch("id", new ExtendedEventSubscription(), Object.class));
  }

  @Test
  void test_tmfPatchWithClassTypeWithRetrievalContext_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.patch(
                "id",
                new ExtendedEventSubscription(),
                RetrievalContext.builder().build(),
                Object.class));
  }

  @Test
  void test_tmfPatchWithTokenWithClassType_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.patchWithToken("token", "id", new ExtendedEventSubscription(), Object.class));
  }

  @Test
  void test_tmfPatchWithTokenWithClassTypeWithRetrievalContext_throwUnsupportedException() {
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.patchWithToken(
                "token",
                "id",
                new ExtendedEventSubscription(),
                RetrievalContext.builder().build(),
                Object.class));
  }

  @Test
  void test_tmfJsonPatch_throwUnsupportedException() throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));
    assertThrows(UnsupportedOperationException.class, () -> hubClient.patch("id", patch));
  }

  @Test
  void test_tmfJsonPatchWithRetrievalContext_throwUnsupportedException()
      throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.patch("id", patch, RetrievalContext.builder().build()));
  }

  @Test
  void test_tmfJsonPatchWithToken_throwUnsupportedException() throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));

    assertThrows(
        UnsupportedOperationException.class, () -> hubClient.patchWithToken("token", "id", patch));
  }

  @Test
  void test_tmfJsonPatchWithTokenWithRetrievalContext_throwUnsupportedException()
      throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));

    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.patchWithToken("token", "id", patch, RetrievalContext.builder().build()));
  }

  @Test
  void test_tmfJsonPatchWithClassType_throwUnsupportedException() throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));
    assertThrows(
        UnsupportedOperationException.class, () -> hubClient.patch("id", patch, Object.class));
  }

  @Test
  void test_tmfJsonPatchWithClassTypeWithRetrievalContext_throwUnsupportedException()
      throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.patch("id", patch, RetrievalContext.builder().build(), Object.class));
  }

  @Test
  void test_tmfJsonPatchWithTokenWithClassType_throwUnsupportedException()
      throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));
    assertThrows(
        UnsupportedOperationException.class,
        () -> hubClient.patchWithToken("token", "id", patch, Object.class));
  }

  @Test
  void test_tmfJsonPatchWithTokenWithClassTypeWithRetrievalContext_throwUnsupportedException()
      throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new ReplaceOperation(new JsonPointer("/name"), new TextNode("test_patch"))));
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hubClient.patchWithToken(
                "token", "id", patch, RetrievalContext.builder().build(), Object.class));
  }

  private URI getCallbackUri() {
    return URI.create(MockServerUtils.BASE_URL + "/listener/serviceCatalogStateChangeEvent");
  }

  private ExtendedEventSubscription buildEventSubscriptionInput() {
    var extendedSub = new ExtendedEventSubscription();
    extendedSub.setCallback(getCallbackUri());
    extendedSub.setQuery("eventType=serviceCatalogStateChangeEvent");
    extendedSub.setId(RandomStringUtils.randomAlphabetic(10));
    TmfClientConfig config = tmfClientConfigurations.getTmfClients().get("sh-hub-client");
    extendedSub.setHubUri(
        URI.create(
            config.getBaseUrl()
                + (config.getContextPath() == null ? "" : config.getContextPath())
                + config.getEndpoint()));
    return extendedSub;
  }

  private EventSubscription buildBaseEventSubscriptionInput() {
    var eventSubscription = new EventSubscription();
    eventSubscription.setCallback(getCallbackUri());
    eventSubscription.setQuery("eventType=serviceCatalogStateChangeEvent");
    eventSubscription.setId(RandomStringUtils.randomAlphabetic(10));
    return eventSubscription;
  }
}
