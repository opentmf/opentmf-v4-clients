package org.opentmf.v4.tmf641.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf641.model.ServiceOrder;
import org.opentmf.v4.tmf641.model.ServiceOrderCreate;
import org.opentmf.v4.tmf641.model.ServiceOrderUpdate;

/**
 * @author Pablo Garcia
 * @see ServiceOrderCreate
 * @see ServiceOrderUpdate
 * @see ServiceOrder
 */
public interface ServiceOrderClient extends TmfClient
    <ServiceOrderCreate, ServiceOrderUpdate, ServiceOrder> {
}
