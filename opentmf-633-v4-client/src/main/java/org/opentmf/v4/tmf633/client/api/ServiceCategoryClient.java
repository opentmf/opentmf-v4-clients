package org.opentmf.v4.tmf633.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf633.model.ServiceCategory;
import org.opentmf.v4.tmf633.model.ServiceCategoryCreate;
import org.opentmf.v4.tmf633.model.ServiceCategoryUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceCategoryCreate
 * @see ServiceCategoryUpdate
 * @see ServiceCategory
 */
public interface ServiceCategoryClient extends TmfClient
    <ServiceCategoryCreate, ServiceCategoryUpdate, ServiceCategory> {
}
