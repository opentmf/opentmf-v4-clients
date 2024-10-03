package com.pia.tmf.v4.tmf652.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf652.model.CancelResourceOrder;
import com.pia.tmf.v4.tmf652.model.CancelResourceOrderCreate;

/**
 * @author Gokhan Demir
 * @see CancelResourceOrderCreate
 * @see CancelResourceOrder
 */
public interface CancelResourceOrderClient extends TmfClient
    <CancelResourceOrderCreate, CancelResourceOrderCreate, CancelResourceOrder> {
}
