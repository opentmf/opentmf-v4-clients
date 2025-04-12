package org.opentmf.v4.tmf620.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf620.model.ProductOffering;
import org.opentmf.v4.tmf620.model.ProductOfferingCreate;
import org.opentmf.v4.tmf620.model.ProductOfferingUpdate;

/**
 * @author Gokhan Demir
 * @see ProductOfferingCreate
 * @see ProductOfferingUpdate
 * @see ProductOffering
 */
public interface ProductOfferingClient extends TmfClient
    <ProductOfferingCreate, ProductOfferingUpdate, ProductOffering> {
}
