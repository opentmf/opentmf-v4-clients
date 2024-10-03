package com.pia.tmf.v4.tmf633.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf633.model.ServiceCategory;
import com.pia.tmf.v4.tmf633.model.ServiceCategoryCreate;
import com.pia.tmf.v4.tmf633.model.ServiceCategoryUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceCategoryCreate
 * @see ServiceCategoryUpdate
 * @see ServiceCategory
 */
public interface ServiceCategoryClient extends TmfClient
    <ServiceCategoryCreate, ServiceCategoryUpdate, ServiceCategory> {
}
