package com.pia.tmf.v4.tmf634.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf634.client.api.ResourceCategoryClient;
import com.pia.tmf.v4.tmf634.exception.ResourceCategoryClientException;
import com.pia.tmf.v4.tmf634.model.ResourceCategory;
import com.pia.tmf.v4.tmf634.model.ResourceCategoryCreate;
import com.pia.tmf.v4.tmf634.model.ResourceCategoryUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class ResourceCategoryClientImpl extends TmfClientBaseImpl
    <ResourceCategoryCreate, ResourceCategoryUpdate, ResourceCategory>
    implements ResourceCategoryClient {

  private final TmfClientConfig clientConfig;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<ResourceCategory> getType() {
    return ResourceCategory.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return ResourceCategoryClientException.class;
  }
}
