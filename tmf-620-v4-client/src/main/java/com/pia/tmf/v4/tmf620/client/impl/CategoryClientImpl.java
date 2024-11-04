package com.pia.tmf.v4.tmf620.client.impl;

import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.common.client.impl.TmfClientBaseImpl;
import com.pia.tmf.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.v4.tmf620.client.api.CategoryClient;
import com.pia.tmf.v4.tmf620.exception.CategoryClientException;
import com.pia.tmf.v4.tmf620.model.Category;
import com.pia.tmf.v4.tmf620.model.CategoryCreate;
import com.pia.tmf.v4.tmf620.model.CategoryUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@Override}))
@RequiredArgsConstructor
public class CategoryClientImpl extends TmfClientBaseImpl
    <CategoryCreate, CategoryUpdate, Category>
    implements CategoryClient {

  private final TmfClientConfig clientConfig;
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
}
