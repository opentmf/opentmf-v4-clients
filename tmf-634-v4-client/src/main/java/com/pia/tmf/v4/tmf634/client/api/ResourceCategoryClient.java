package com.pia.tmf.v4.tmf634.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf634.model.ResourceCategory;
import com.pia.tmf.v4.tmf634.model.ResourceCategoryCreate;
import com.pia.tmf.v4.tmf634.model.ResourceCategoryUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCategoryCreate
 * @see ResourceCategoryUpdate
 * @see ResourceCategory
 */
public interface ResourceCategoryClient extends TmfClient
    <ResourceCategoryCreate, ResourceCategoryUpdate, ResourceCategory> {
}
