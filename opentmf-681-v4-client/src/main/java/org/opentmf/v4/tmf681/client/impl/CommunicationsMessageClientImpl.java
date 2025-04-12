package org.opentmf.v4.tmf681.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf681.client.api.CommunicationsMessageClient;
import org.opentmf.v4.tmf681.exception.CommunicationsMessageClientException;
import org.opentmf.v4.tmf681.model.CommunicationMessage;
import org.opentmf.v4.tmf681.model.CommunicationMessageCreate;
import org.opentmf.v4.tmf681.model.CommunicationMessageUpdate;
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
