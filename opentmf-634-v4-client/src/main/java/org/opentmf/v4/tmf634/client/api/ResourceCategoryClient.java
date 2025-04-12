package org.opentmf.v4.tmf634.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf634.model.ResourceCategory;
import org.opentmf.v4.tmf634.model.ResourceCategoryCreate;
import org.opentmf.v4.tmf634.model.ResourceCategoryUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCategoryCreate
 * @see ResourceCategoryUpdate
 * @see ResourceCategory
 */
public interface ResourceCategoryClient extends TmfClient
    <ResourceCategoryCreate, ResourceCategoryUpdate, ResourceCategory> {
}
