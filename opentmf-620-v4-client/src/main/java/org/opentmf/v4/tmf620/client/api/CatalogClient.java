package org.opentmf.v4.tmf620.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf620.model.Catalog;
import org.opentmf.v4.tmf620.model.CatalogCreate;
import org.opentmf.v4.tmf620.model.CatalogUpdate;

/**
 * @author Gokhan Demir
 * @see CatalogCreate
 * @see CatalogUpdate
 * @see Catalog
 */
public interface CatalogClient extends TmfClient
    <CatalogCreate, CatalogUpdate, Catalog> {
}
