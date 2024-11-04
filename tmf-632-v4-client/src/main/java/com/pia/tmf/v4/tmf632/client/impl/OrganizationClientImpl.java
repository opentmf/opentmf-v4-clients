package com.pia.tmf.v4.tmf632.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf632.client.api.OrganizationClient;
import com.pia.tmf.v4.tmf632.exception.OrganizationClientException;
import com.pia.tmf.v4.tmf632.model.Organization;
import com.pia.tmf.v4.tmf632.model.OrganizationCreate;
import com.pia.tmf.v4.tmf632.model.OrganizationUpdate;
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
