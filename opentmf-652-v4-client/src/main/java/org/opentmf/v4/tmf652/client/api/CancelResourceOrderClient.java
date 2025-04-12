package org.opentmf.v4.tmf652.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf652.model.CancelResourceOrder;
import org.opentmf.v4.tmf652.model.CancelResourceOrderCreate;

/**
 * @author Gokhan Demir
 * @see CancelResourceOrderCreate
 * @see CancelResourceOrder
 */
public interface CancelResourceOrderClient extends TmfClient
    <CancelResourceOrderCreate, CancelResourceOrderCreate, CancelResourceOrder> {
}
