package com.pia.tmf.v4.tmf620.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf620.model.ProductOffering;
import com.pia.tmf.v4.tmf620.model.ProductOfferingCreate;
import com.pia.tmf.v4.tmf620.model.ProductOfferingUpdate;

/**
 * @author Gokhan Demir
 * @see ProductOfferingCreate
 * @see ProductOfferingUpdate
 * @see ProductOffering
 */
public interface ProductOfferingClient extends TmfClient
    <ProductOfferingCreate, ProductOfferingUpdate, ProductOffering> {
}
