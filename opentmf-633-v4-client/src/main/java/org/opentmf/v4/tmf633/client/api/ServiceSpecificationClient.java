package org.opentmf.v4.tmf633.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf633.model.ServiceSpecification;
import org.opentmf.v4.tmf633.model.ServiceSpecificationCreate;
import org.opentmf.v4.tmf633.model.ServiceSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceSpecificationCreate
 * @see ServiceSpecificationUpdate
 * @see ServiceSpecification
 */
public interface ServiceSpecificationClient extends TmfClient
    <ServiceSpecificationCreate, ServiceSpecificationUpdate, ServiceSpecification> {
}
