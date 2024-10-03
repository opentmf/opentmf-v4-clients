package com.pia.tmf.v4.tmf634.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf634.model.ResourceSpecification;
import com.pia.tmf.v4.tmf634.model.ResourceSpecificationCreate;
import com.pia.tmf.v4.tmf634.model.ResourceSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceSpecificationCreate
 * @see ResourceSpecificationUpdate
 * @see ResourceSpecification
 */
public interface ResourceSpecificationClient extends TmfClient
    <ResourceSpecificationCreate, ResourceSpecificationUpdate, ResourceSpecification> {
}
