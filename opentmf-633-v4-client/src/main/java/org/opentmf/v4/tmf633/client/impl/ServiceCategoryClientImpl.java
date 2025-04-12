package org.opentmf.v4.tmf633.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf633.client.api.ServiceCategoryClient;
import org.opentmf.v4.tmf633.exception.ServiceCategoryClientException;
import org.opentmf.v4.tmf633.model.ServiceCategory;
import org.opentmf.v4.tmf633.model.ServiceCategoryCreate;
import org.opentmf.v4.tmf633.model.ServiceCategoryUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ServiceCategoryClientImpl extends TmfClientBaseImpl
    <ServiceCategoryCreate, ServiceCategoryUpdate, ServiceCategory>
    implements ServiceCategoryClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ServiceCategory> getType() {
    return ServiceCategory.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ServiceCategoryClientException.class;
  }
}
