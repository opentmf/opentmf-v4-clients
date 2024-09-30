package com.pia.tmf.v4.tmf622.client;

import com.pia.dnext.v4.tmf622.model.DnextProductOrder;
import com.pia.dnext.v4.tmf622.model.DnextProductOrderItem;
import com.pia.tmf.v4.tmf622.model.ProductOrderItem;
import org.junit.jupiter.api.Assertions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Cezmi Aslan
 */
public class ProductOrderClientTestUtil {
  static void verifyOk(Mono<DnextProductOrder> response) {
    StepVerifier.create(response)
        .assertNext(
            returned -> {
              Assertions.assertNotNull(returned);
              Assertions.assertNotNull( returned.getId());
              verifyCommonFields(returned);
            })
        .verifyComplete();
  }

  static void verifyOrderItemOk(Mono<DnextProductOrderItem> response, String orderItemId) {
    StepVerifier.create(response)
        .assertNext(
            returned -> {
              Assertions.assertNotNull(returned);
              Assertions.assertEquals(orderItemId, returned.getId());
            })
        .verifyComplete();
  }

  static void verifyListOrderItemOk(Flux<ProductOrderItem> response, String orderItemId) {
    StepVerifier.create(response)
        .assertNext(
            returned -> {
              Assertions.assertNotNull(returned);
              Assertions.assertEquals(orderItemId, returned.getId());
            })
        .verifyComplete();
  }

  static void verifyListProductOrderOk(Flux<DnextProductOrder> response, String orderItemId) {
    StepVerifier.create(response)
        .assertNext(
            returned -> {
              Assertions.assertNotNull(returned);
              Assertions.assertEquals(orderItemId, returned.getId());
            })
        .verifyComplete();
  }

  static void verifyCommonFields(DnextProductOrder returned) {
    Assertions.assertNotNull(returned.getOrderCharacteristic());
    Assertions.assertFalse(returned.getOrderCharacteristic().isEmpty());
    Assertions.assertNotNull(returned.getProductOrderItems());
    Assertions.assertFalse(returned.getProductOrderItems().isEmpty());
    for (ProductOrderItem item : returned.getProductOrderItems()) {
      var dnextItem = (DnextProductOrderItem) item;
      Assertions.assertNotNull(dnextItem.getOrderItemCharacteristic());
      Assertions.assertFalse(dnextItem.getOrderItemCharacteristic().isEmpty());
    }
  }
}
