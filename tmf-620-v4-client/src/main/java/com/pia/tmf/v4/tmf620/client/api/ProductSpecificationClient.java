package com.pia.tmf.v4.tmf620.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf620.model.ProductSpecification;
import com.pia.tmf.v4.tmf620.model.ProductSpecificationCreate;
import com.pia.tmf.v4.tmf620.model.ProductSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see ProductSpecificationCreate
 * @see ProductSpecificationUpdate
 * @see ProductSpecification
 */
public interface ProductSpecificationClient extends TmfClient
    <ProductSpecificationCreate, ProductSpecificationUpdate, ProductSpecification> {
}
