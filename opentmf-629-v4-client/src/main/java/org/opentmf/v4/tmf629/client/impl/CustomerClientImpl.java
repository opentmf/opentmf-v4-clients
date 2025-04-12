package org.opentmf.v4.tmf629.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf629.client.api.CustomerClient;
import org.opentmf.v4.tmf629.exception.CustomerClientException;
import org.opentmf.v4.tmf629.model.Customer;
import org.opentmf.v4.tmf629.model.CustomerCreate;
import org.opentmf.v4.tmf629.model.CustomerUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class CustomerClientImpl extends TmfClientBaseImpl
    <CustomerCreate, CustomerUpdate, Customer>
    implements CustomerClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Customer> getType() {
    return Customer.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CustomerClientException.class;
  }
}
