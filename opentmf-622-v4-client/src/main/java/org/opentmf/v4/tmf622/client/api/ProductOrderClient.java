package org.opentmf.v4.tmf622.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf622.model.ProductOrder;
import org.opentmf.v4.tmf622.model.ProductOrderCreate;
import org.opentmf.v4.tmf622.model.ProductOrderUpdate;

/**
 * @author Gokhan Demir
 * @see ProductOrderCreate
 * @see ProductOrderUpdate
 * @see ProductOrder
 */
public interface ProductOrderClient extends TmfClient
    <ProductOrderCreate, ProductOrderUpdate, ProductOrder> {
}
