package org.opentmf.v4.tmf648.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf648.client.api.QuoteClient;
import org.opentmf.v4.tmf648.exception.QuoteClientException;
import org.opentmf.v4.tmf648.model.Quote;
import org.opentmf.v4.tmf648.model.QuoteCreate;
import org.opentmf.v4.tmf648.model.QuoteUpdate;
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
