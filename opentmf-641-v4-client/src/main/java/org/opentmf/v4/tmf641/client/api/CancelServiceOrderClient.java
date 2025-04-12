package org.opentmf.v4.tmf641.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf641.model.CancelServiceOrder;
import org.opentmf.v4.tmf641.model.CancelServiceOrderCreate;

/**
 * @author Gokhan Demir
 * @see CancelServiceOrderCreate
 * @see CancelServiceOrder
 */
public interface CancelServiceOrderClient extends TmfClient
    <CancelServiceOrderCreate, CancelServiceOrderCreate, CancelServiceOrder> {
}
