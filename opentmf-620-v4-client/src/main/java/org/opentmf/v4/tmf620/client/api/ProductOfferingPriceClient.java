package org.opentmf.v4.tmf620.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf620.model.ProductOfferingPrice;
import org.opentmf.v4.tmf620.model.ProductOfferingPriceCreate;
import org.opentmf.v4.tmf620.model.ProductOfferingPriceUpdate;

/**
 * @author Gokhan Demir
 * @see ProductOfferingPriceCreate
 * @see ProductOfferingPriceUpdate
 * @see ProductOfferingPrice
 */
public interface ProductOfferingPriceClient extends TmfClient
    <ProductOfferingPriceCreate, ProductOfferingPriceUpdate, ProductOfferingPrice> {
}
