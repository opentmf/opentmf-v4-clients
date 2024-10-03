package com.pia.tmf.v4.tmf641;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author Pablo Garcia
 */
@SpringBootTest
@RequiredArgsConstructor
class Tmf641ClientApplicationTests {
  private final ApplicationContext ctx;
  @Test
  void contextLoads() {
    Assertions.assertNotNull(ctx);
  }
}
