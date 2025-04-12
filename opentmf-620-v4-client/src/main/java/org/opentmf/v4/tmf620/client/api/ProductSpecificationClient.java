package org.opentmf.v4.tmf620.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf620.model.ProductSpecification;
import org.opentmf.v4.tmf620.model.ProductSpecificationCreate;
import org.opentmf.v4.tmf620.model.ProductSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see ProductSpecificationCreate
 * @see ProductSpecificationUpdate
 * @see ProductSpecification
 */
public interface ProductSpecificationClient extends TmfClient
    <ProductSpecificationCreate, ProductSpecificationUpdate, ProductSpecification> {
}
