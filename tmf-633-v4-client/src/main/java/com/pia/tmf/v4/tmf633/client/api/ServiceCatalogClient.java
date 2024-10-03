package com.pia.tmf.v4.tmf633.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf633.model.ServiceCatalog;
import com.pia.tmf.v4.tmf633.model.ServiceCatalogCreate;
import com.pia.tmf.v4.tmf633.model.ServiceCatalogUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceCatalogCreate
 * @see ServiceCatalogUpdate
 * @see ServiceCatalog
 */
public interface ServiceCatalogClient extends TmfClient
    <ServiceCatalogCreate, ServiceCatalogUpdate, ServiceCatalog> {
}
