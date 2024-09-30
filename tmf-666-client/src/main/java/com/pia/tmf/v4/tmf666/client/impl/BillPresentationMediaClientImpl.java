package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.BillPresentationMediaClient;
import com.pia.tmf.v4.tmf666.exception.BillPresentationMediaClientException;
import com.pia.tmf.v4.tmf666.model.BillPresentationMedia;
import com.pia.tmf.v4.tmf666.model.BillPresentationMediaCreate;
import com.pia.tmf.v4.tmf666.model.BillPresentationMediaUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class BillPresentationMediaClientImpl extends TmfClientBaseImpl
    <BillPresentationMediaCreate, BillPresentationMediaUpdate, BillPresentationMedia>
    implements BillPresentationMediaClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<BillPresentationMedia> getType() {
    return BillPresentationMedia.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return BillPresentationMediaClientException.class;
  }

  @Override
  protected TmfClientConfig getClientConfig() {
    return this.config;
  }

  @Override
  protected WebClient getWebClient() {
    return this.webClient;
  }

  @Override
  protected TokenService getTokenService() {
    return this.tokenService;
  }

  @Override
  protected BaseClientProperties getClientProperties() {
    return this.clientProperties;
  }
}
