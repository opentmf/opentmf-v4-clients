package org.opentmf.v4.tmf666.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf666.client.api.BillPresentationMediaClient;
import org.opentmf.v4.tmf666.exception.BillPresentationMediaClientException;
import org.opentmf.v4.tmf666.model.BillPresentationMedia;
import org.opentmf.v4.tmf666.model.BillPresentationMediaCreate;
import org.opentmf.v4.tmf666.model.BillPresentationMediaUpdate;
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
