package com.pia.tmf.v4.tmf620.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf620.model.ProductOfferingPrice;
import com.pia.tmf.v4.tmf620.model.ProductOfferingPriceCreate;
import com.pia.tmf.v4.tmf620.model.ProductOfferingPriceUpdate;

/**
 * @author Gokhan Demir
 * @see ProductOfferingPriceCreate
 * @see ProductOfferingPriceUpdate
 * @see ProductOfferingPrice
 */
public interface ProductOfferingPriceClient extends TmfClient
    <ProductOfferingPriceCreate, ProductOfferingPriceUpdate, ProductOfferingPrice> {
}
