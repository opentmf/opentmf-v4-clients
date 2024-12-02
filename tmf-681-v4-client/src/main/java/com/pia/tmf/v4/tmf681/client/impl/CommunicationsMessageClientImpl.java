package com.pia.tmf.v4.tmf681.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf681.client.api.CommunicationsMessageClient;
import com.pia.tmf.v4.tmf681.exception.CommunicationsMessageClientException;
import com.pia.tmf.v4.tmf681.model.CommunicationMessage;
import com.pia.tmf.v4.tmf681.model.CommunicationMessageCreate;
import com.pia.tmf.v4.tmf681.model.CommunicationMessageUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class CommunicationsMessageClientImpl extends TmfClientBaseImpl
    <CommunicationMessageCreate, CommunicationMessageUpdate, CommunicationMessage>
    implements CommunicationsMessageClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<CommunicationMessage> getType() {
    return CommunicationMessage.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CommunicationsMessageClientException.class;
  }
}
