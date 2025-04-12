package org.opentmf.v4.tmf634.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf634.model.ResourceCatalog;
import org.opentmf.v4.tmf634.model.ResourceCatalogCreate;
import org.opentmf.v4.tmf634.model.ResourceCatalogUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCatalogCreate
 * @see ResourceCatalogUpdate
 * @see ResourceCatalog
 */
public interface ResourceCatalogClient extends TmfClient
    <ResourceCatalogCreate, ResourceCatalogUpdate, ResourceCatalog> {
}
