package com.pia.tmf.v4.common.config;

import static com.pia.tmf.v4.common.util.TmfClientConstants.CLIENT_PROPERTIES;
import static com.pia.tmf.v4.common.util.TmfClientConstants.TOKEN_SERVICE;
import static com.pia.tmf.v4.common.util.TmfClientConstants.WEB_CLIENT;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pia.client.common.model.BaseClientProperties;
import com.pia.client.common.service.api.TokenService;
import com.pia.tmf.v4.common.client.TmfClientProvider;
import com.pia.tmf.v4.common.config.TmfClientConfigurations.TmfClientConfig;
import com.pia.tmf.v4.common.helper.TestResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Gokhan Demir
 */
@RequiredArgsConstructor
public class TestClientProvider implements TmfClientProvider<
    TestTmfClient<ObjectNode, TestResponseModel, TestResponseModel>> {

  private final ApplicationContext ctx;

  @Override
  public TestTmfClient<ObjectNode, TestResponseModel, TestResponseModel>
  getTmfClient(TmfClientConfig config, String clientId) {
    return new TestTmfClientImpl<>(TestResponseModel.class,
        config,
        ctx.getBean(clientId + WEB_CLIENT, WebClient.class),
        ctx.getBean(clientId + TOKEN_SERVICE, TokenService.class),
        ctx.getBean(clientId + CLIENT_PROPERTIES, BaseClientProperties.class));
  }
}
