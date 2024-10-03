package com.pia.tmf.v4.tmf622.test.util.config;

import static com.pia.commons.util.JacksonUtil.getDefaultObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pia.dnext.v4.tmf622.config.DnextTmf622JacksonConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Cezmi Aslan
 */
@Configuration
public class JacksonConfig {

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    var objectMapper = getDefaultObjectMapper();
    DnextTmf622JacksonConfig.registerExtensions(objectMapper);
    return objectMapper;
  }
}
