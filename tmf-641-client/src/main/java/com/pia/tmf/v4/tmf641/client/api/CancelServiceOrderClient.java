package com.pia.tmf.v4.tmf641.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf641.model.CancelServiceOrder;
import com.pia.tmf.v4.tmf641.model.CancelServiceOrderCreate;

/**
 * @author Gokhan Demir
 * @see CancelServiceOrderCreate
 * @see CancelServiceOrder
 */
public interface CancelServiceOrderClient extends TmfClient
    <CancelServiceOrderCreate, CancelServiceOrderCreate, CancelServiceOrder> {
}
