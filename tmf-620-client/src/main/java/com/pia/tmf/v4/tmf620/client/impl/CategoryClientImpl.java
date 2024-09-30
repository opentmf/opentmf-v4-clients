package com.pia.tmf.v4.tmf620.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf620.client.api.CategoryClient;
import com.pia.tmf.v4.tmf620.exception.CategoryClientException;
import com.pia.tmf.v4.tmf620.model.Category;
import com.pia.tmf.v4.tmf620.model.CategoryCreate;
import com.pia.tmf.v4.tmf620.model.CategoryUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class CategoryClientImpl extends TmfClientBaseImpl
    <CategoryCreate, CategoryUpdate, Category>
    implements CategoryClient {

  private final TmfClientConfig config;
  private final WebClient webClient;
  private final TokenService tokenService;
  private final BaseClientProperties clientProperties;

  @Override
  protected Class<Category> getType() {
    return Category.class;
  }

  @Override
  protected Class<? extends TmfClientException> getExceptionType() {
    return CategoryClientException.class;
  }

  @Override
  protected TmfClientConfig getClientConfig() {
    return this.config;
  }

  @Override
  protected WebClient getWebClient() {
    return this.webClient;
  }

  @Override
  protected TokenService getTokenService() {
    return this.tokenService;
  }

  @Override
  protected BaseClientProperties getClientProperties() {
    return this.clientProperties;
  }
}
