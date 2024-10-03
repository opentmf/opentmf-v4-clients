package com.pia.tmf.v4.tmf652.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf652.model.ResourceOrder;
import com.pia.tmf.v4.tmf652.model.ResourceOrderCreate;
import com.pia.tmf.v4.tmf652.model.ResourceOrderUpdate;

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
