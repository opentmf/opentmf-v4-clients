package com.pia.tmf.v4.tmf633.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf633.model.ServiceSpecification;
import com.pia.tmf.v4.tmf633.model.ServiceSpecificationCreate;
import com.pia.tmf.v4.tmf633.model.ServiceSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceSpecificationCreate
 * @see ServiceSpecificationUpdate
 * @see ServiceSpecification
 */
public interface ServiceSpecificationClient extends TmfClient
    <ServiceSpecificationCreate, ServiceSpecificationUpdate, ServiceSpecification> {
}
