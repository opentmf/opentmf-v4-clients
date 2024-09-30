package com.pia.tmf.v4.common.util;

import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.model.RetrievalContext;
import com.pia.tmf.v4.common.model.TmfClientCommonsConstants;
import com.pia.tmf.v4.common.model.TmfOffsetRequest;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TmfClientCommonHeaderUtil {
  private TmfClientCommonHeaderUtil() {}

  public static Consumer<HttpHeaders> prepareHeaderConsumer(String authToken,
      TokenService tokenService) {
    validateAuthToken(authToken);
    return setAuthorization(authToken, tokenService);
  }

  public static Consumer<HttpHeaders> prepareHeaderConsumer(
      String authToken, TmfClientConfig config, TokenService tokenService) {
    validateAuthToken(authToken);
    return combineConsumers(
        setAuthorization(authToken, tokenService),
        addTmfConfigFixedHeaders(config)
    );
  }

  public static Consumer<HttpHeaders> prepareHeaderConsumer(String authToken, Pageable pageQuery,
      TokenService tokenService) {
    if (pageQuery instanceof TmfOffsetRequest tmfOffsetRequest) {
      return prepareHeaderConsumer(authToken, tmfOffsetRequest.getRetrievalContext(), tokenService);
    }
    return prepareHeaderConsumer(authToken, tokenService);
  }

  public static Consumer<HttpHeaders> prepareHeaderConsumer(
      String authToken, TmfClientConfig config, Pageable pageQuery, TokenService tokenService) {
    if (pageQuery instanceof TmfOffsetRequest tmfOffsetRequest) {
      return prepareHeaderConsumer(authToken, config, tmfOffsetRequest.getRetrievalContext(), tokenService);
    }
    return prepareHeaderConsumer(authToken, config, tokenService);
  }

  public static Consumer<HttpHeaders> prepareHeaderConsumer(
      String authToken, RetrievalContext retrievalContext, TokenService tokenService) {
    validateAuthToken(authToken);
    return combineConsumers(
        setAuthorization(authToken, tokenService),
        addRetrievalContextHeaders(retrievalContext));
  }

  public static Consumer<HttpHeaders> prepareHeaderConsumer(
      String authToken,
      TmfClientConfig config,
      RetrievalContext retrievalContext, TokenService tokenService) {
    validateAuthToken(authToken);
    return combineConsumers(
        setAuthorization(authToken, tokenService),
        addRetrievalContextHeaders(retrievalContext),
        addTmfConfigFixedHeaders(config));
  }

  private static Consumer<HttpHeaders> setAuthorization(String authToken, TokenService tokenService) {
    return httpHeaders -> httpHeaders.set(HttpHeaders.AUTHORIZATION,
        tokenService.getTokenType() + " " + authToken);
  }

  private static Consumer<HttpHeaders> addRetrievalContextHeaders(
      RetrievalContext retrievalContext) {
    return httpHeaders -> {
      if (retrievalContext != null) {
        addHeaders(httpHeaders, retrievalContext.getHeaderParameters());
      }
    };
  }

  private static Consumer<HttpHeaders> addTmfConfigFixedHeaders(
      TmfClientConfig tmfClientConfig) {
    return httpHeaders -> {
      if (tmfClientConfig != null) {
        addHeaders(httpHeaders, tmfClientConfig.getFixedHeaders());
      }
    };
  }

  private static void addHeaders(HttpHeaders httpHeaders, Map<String, String> headers) {
    if (headers != null && !headers.isEmpty()) {
      MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
      headers.forEach(multiValueMap::add);
      httpHeaders.addAll(multiValueMap);
    }
  }

  private static void addHeaders(HttpHeaders httpHeaders, MultiValueMap<String, String> headers) {
    if (headers != null && !headers.isEmpty()) {
      httpHeaders.addAll(headers);
    }
  }

  private static void validateAuthToken(String authToken) {
    if (StringUtils.isBlank(authToken)) {
      throw new IllegalArgumentException(TmfClientCommonsConstants.ERROR_MSG_EMPTY_AUTH_TOKEN);
    }
  }

  public static String getHeadersFirstValue(String key, ResponseEntity<?> responseEntity) {
    var headers = responseEntity.getHeaders();
    if (headers.containsKey(key)) {
      var value = headers.getFirst(key);
      return value == null ? "" : value.trim();
    }
    return null;
  }

  @SafeVarargs
  private static Consumer<HttpHeaders> combineConsumers(Consumer<HttpHeaders>... consumers) {
    return httpHeaders -> {
      for (Consumer<HttpHeaders> consumer : consumers) {
        consumer.accept(httpHeaders);
      }
    };
  }
}
