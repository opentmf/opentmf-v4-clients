package org.opentmf.v4.tmf622.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf622.model.CancelProductOrder;
import org.opentmf.v4.tmf622.model.CancelProductOrderCreate;

/**
 * @author Gokhan Demir
 * @see CancelProductOrderCreate
 * @see CancelProductOrder
 */
public interface CancelProductOrderClient extends TmfClient
    <CancelProductOrderCreate, CancelProductOrderCreate, CancelProductOrder> {
}
