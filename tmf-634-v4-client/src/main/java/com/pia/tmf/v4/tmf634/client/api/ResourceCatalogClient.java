package com.pia.tmf.v4.tmf634.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf634.model.ResourceCatalog;
import com.pia.tmf.v4.tmf634.model.ResourceCatalogCreate;
import com.pia.tmf.v4.tmf634.model.ResourceCatalogUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCatalogCreate
 * @see ResourceCatalogUpdate
 * @see ResourceCatalog
 */
public interface ResourceCatalogClient extends TmfClient
    <ResourceCatalogCreate, ResourceCatalogUpdate, ResourceCatalog> {
}
