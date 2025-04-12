package org.opentmf.v4.tmf652.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf652.model.ResourceOrder;
import org.opentmf.v4.tmf652.model.ResourceOrderCreate;
import org.opentmf.v4.tmf652.model.ResourceOrderUpdate;

/**
 * @author Gokhan Demir
 * @author Yusuf BOZKURT
 * @see ResourceOrderCreate
 * @see ResourceOrderUpdate
 * @see ResourceOrder
 */
public interface ResourceOrderClient extends TmfClient
    <ResourceOrderCreate, ResourceOrderUpdate, ResourceOrder> {
}
