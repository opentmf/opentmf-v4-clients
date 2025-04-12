package org.opentmf.v4.tmf666.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf666.client.api.BillFormatClient;
import org.opentmf.v4.tmf666.exception.BillFormatClientException;
import org.opentmf.v4.tmf666.model.BillFormat;
import org.opentmf.v4.tmf666.model.BillFormatCreate;
import org.opentmf.v4.tmf666.model.BillFormatUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class BillFormatClientImpl extends TmfClientBaseImpl
    <BillFormatCreate, BillFormatUpdate, BillFormat>
    implements BillFormatClient {

  private final TmfClientConfig clientConfig;
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
}
