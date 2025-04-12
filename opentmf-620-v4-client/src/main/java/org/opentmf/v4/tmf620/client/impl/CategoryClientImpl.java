package org.opentmf.v4.tmf620.client.impl;

import org.opentmf.client.common.model.BaseClientProperties;
import org.opentmf.client.common.service.api.TokenService;
import org.opentmf.common.client.impl.TmfClientBaseImpl;
import org.opentmf.common.config.TmfClientConfigurations.TmfClientConfig;
import org.opentmf.common.exception.TmfClientException;
import org.opentmf.v4.tmf620.client.api.CategoryClient;
import org.opentmf.v4.tmf620.exception.CategoryClientException;
import org.opentmf.v4.tmf620.model.Category;
import org.opentmf.v4.tmf620.model.CategoryCreate;
import org.opentmf.v4.tmf620.model.CategoryUpdate;
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
