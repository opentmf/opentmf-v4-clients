package com.pia.tmf.v4.tmf639.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.resource.model.Resource;
import com.pia.tmf.v4.resource.model.ResourceCreate;
import com.pia.tmf.v4.resource.model.ResourceUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCreate
 * @see ResourceUpdate
 * @see Resource
 */
public interface ResourceClient extends TmfClient
    <ResourceCreate, ResourceUpdate, Resource> {
}
