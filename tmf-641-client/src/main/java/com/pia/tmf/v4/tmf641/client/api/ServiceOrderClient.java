package com.pia.tmf.v4.tmf641.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf641.model.ServiceOrder;
import com.pia.tmf.v4.tmf641.model.ServiceOrderCreate;
import com.pia.tmf.v4.tmf641.model.ServiceOrderUpdate;

/**
 * @author Pablo Garcia
 * @see ServiceOrderCreate
 * @see ServiceOrderUpdate
 * @see ServiceOrder
 */
public interface ServiceOrderClient extends TmfClient
    <ServiceOrderCreate, ServiceOrderUpdate, ServiceOrder> {
}
