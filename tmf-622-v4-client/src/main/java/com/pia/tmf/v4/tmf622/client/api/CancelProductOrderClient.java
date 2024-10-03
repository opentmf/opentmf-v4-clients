package com.pia.tmf.v4.tmf622.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf622.model.CancelProductOrder;
import com.pia.tmf.v4.tmf622.model.CancelProductOrderCreate;

/**
 * @author Gokhan Demir
 * @see CancelProductOrderCreate
 * @see CancelProductOrder
 */
public interface CancelProductOrderClient extends TmfClient
    <CancelProductOrderCreate, CancelProductOrderCreate, CancelProductOrder> {
}
