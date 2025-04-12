package org.opentmf.v4.tmf634.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf634.model.ResourceSpecification;
import org.opentmf.v4.tmf634.model.ResourceSpecificationCreate;
import org.opentmf.v4.tmf634.model.ResourceSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceSpecificationCreate
 * @see ResourceSpecificationUpdate
 * @see ResourceSpecification
 */
public interface ResourceSpecificationClient extends TmfClient
    <ResourceSpecificationCreate, ResourceSpecificationUpdate, ResourceSpecification> {
}
