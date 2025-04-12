package org.opentmf.v4.tmf632.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf632.client.api.OrganizationClient;
import org.opentmf.v4.tmf632.exception.OrganizationClientException;
import org.opentmf.v4.tmf632.model.Organization;
import org.opentmf.v4.tmf632.model.OrganizationCreate;
import org.opentmf.v4.tmf632.model.OrganizationUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class OrganizationClientImpl extends TmfClientBaseImpl
    <OrganizationCreate, OrganizationUpdate, Organization>
    implements OrganizationClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Organization> getType() {
    return Organization.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return OrganizationClientException.class;
  }
}
