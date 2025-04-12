package org.opentmf.v4.tmf633.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf633.model.ServiceCatalog;
import org.opentmf.v4.tmf633.model.ServiceCatalogCreate;
import org.opentmf.v4.tmf633.model.ServiceCatalogUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceCatalogCreate
 * @see ServiceCatalogUpdate
 * @see ServiceCatalog
 */
public interface ServiceCatalogClient extends TmfClient
    <ServiceCatalogCreate, ServiceCatalogUpdate, ServiceCatalog> {
}
