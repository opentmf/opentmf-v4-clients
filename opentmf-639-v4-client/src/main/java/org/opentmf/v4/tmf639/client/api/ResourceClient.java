package org.opentmf.v4.tmf639.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.resource.model.Resource;
import org.opentmf.v4.resource.model.ResourceCreate;
import org.opentmf.v4.resource.model.ResourceUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCreate
 * @see ResourceUpdate
 * @see Resource
 */
public interface ResourceClient extends TmfClient
    <ResourceCreate, ResourceUpdate, Resource> {
}
