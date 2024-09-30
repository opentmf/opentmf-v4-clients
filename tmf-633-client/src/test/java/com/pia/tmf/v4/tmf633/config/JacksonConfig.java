package com.pia.tmf.v4.tmf633.config;

import static com.pia.commons.util.JacksonUtil.getDefaultObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    Tmf633JacksonConfig.registerExtensions(objectMapper);
    return objectMapper;
  }
}
