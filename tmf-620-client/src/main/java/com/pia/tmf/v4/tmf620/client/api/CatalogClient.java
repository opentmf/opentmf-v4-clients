package com.pia.tmf.v4.tmf620.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf620.model.Catalog;
import com.pia.tmf.v4.tmf620.model.CatalogCreate;
import com.pia.tmf.v4.tmf620.model.CatalogUpdate;

/**
 * @author Gokhan Demir
 * @see CatalogCreate
 * @see CatalogUpdate
 * @see Catalog
 */
public interface CatalogClient extends TmfClient
    <CatalogCreate, CatalogUpdate, Catalog> {
}
