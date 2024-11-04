package com.pia.tmf.v4.tmf666.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf666.client.api.BillPresentationMediaClient;
import com.pia.tmf.v4.tmf666.exception.BillPresentationMediaClientException;
import com.pia.tmf.v4.tmf666.model.BillPresentationMedia;
import com.pia.tmf.v4.tmf666.model.BillPresentationMediaCreate;
import com.pia.tmf.v4.tmf666.model.BillPresentationMediaUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class BillPresentationMediaClientImpl extends TmfClientBaseImpl
    <BillPresentationMediaCreate, BillPresentationMediaUpdate, BillPresentationMedia>
    implements BillPresentationMediaClient {

  private final TmfClientConfig clientConfig;
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
}
