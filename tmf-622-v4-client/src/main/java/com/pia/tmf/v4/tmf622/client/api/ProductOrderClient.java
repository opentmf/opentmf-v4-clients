package com.pia.tmf.v4.tmf622.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf622.model.ProductOrder;
import com.pia.tmf.v4.tmf622.model.ProductOrderCreate;
import com.pia.tmf.v4.tmf622.model.ProductOrderUpdate;

/**
 * @author Gokhan Demir
 * @see ProductOrderCreate
 * @see ProductOrderUpdate
 * @see ProductOrder
 */
public interface ProductOrderClient extends TmfClient
    <ProductOrderCreate, ProductOrderUpdate, ProductOrder> {
}
