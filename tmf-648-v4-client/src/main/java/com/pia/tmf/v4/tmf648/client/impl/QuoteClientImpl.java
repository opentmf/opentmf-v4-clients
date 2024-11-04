package com.pia.tmf.v4.tmf648.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf648.client.api.QuoteClient;
import com.pia.tmf.v4.tmf648.exception.QuoteClientException;
import com.pia.tmf.v4.tmf648.model.Quote;
import com.pia.tmf.v4.tmf648.model.QuoteCreate;
import com.pia.tmf.v4.tmf648.model.QuoteUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class QuoteClientImpl extends TmfClientBaseImpl
    <QuoteCreate, QuoteUpdate, Quote>
    implements QuoteClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Quote> getType() {
    return Quote.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return QuoteClientException.class;
  }
}
