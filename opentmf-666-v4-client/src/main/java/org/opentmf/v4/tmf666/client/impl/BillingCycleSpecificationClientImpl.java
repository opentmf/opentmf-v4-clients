package org.opentmf.v4.tmf666.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf666.client.api.BillingCycleSpecificationClient;
import org.opentmf.v4.tmf666.exception.BillingCycleSpecificationClientException;
import org.opentmf.v4.tmf666.model.BillingCycleSpecification;
import org.opentmf.v4.tmf666.model.BillingCycleSpecificationCreate;
import org.opentmf.v4.tmf666.model.BillingCycleSpecificationUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class BillingCycleSpecificationClientImpl extends TmfClientBaseImpl
    <BillingCycleSpecificationCreate, BillingCycleSpecificationUpdate, BillingCycleSpecification>
    implements BillingCycleSpecificationClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<BillingCycleSpecification> getType() {
    return BillingCycleSpecification.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return BillingCycleSpecificationClientException.class;
  }
}
