package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.BillFormatClient;
import com.pia.tmf.v4.tmf666.exception.BillFormatClientException;
import com.pia.tmf.v4.tmf666.model.BillFormat;
import com.pia.tmf.v4.tmf666.model.BillFormatCreate;
import com.pia.tmf.v4.tmf666.model.BillFormatUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class BillFormatClientImpl extends TmfClientBaseImpl
    <BillFormatCreate, BillFormatUpdate, BillFormat>
    implements BillFormatClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<BillFormat> getType() {
    return BillFormat.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return BillFormatClientException.class;
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
