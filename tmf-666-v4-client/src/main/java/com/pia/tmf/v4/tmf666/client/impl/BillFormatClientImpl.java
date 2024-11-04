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
