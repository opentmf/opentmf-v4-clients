package org.opentmf.v4.tmf666.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf666.model.BillingCycleSpecification;
import org.opentmf.v4.tmf666.model.BillingCycleSpecificationCreate;
import org.opentmf.v4.tmf666.model.BillingCycleSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see BillingCycleSpecificationCreate
 * @see BillingCycleSpecificationUpdate
 * @see BillingCycleSpecification
 */
public interface BillingCycleSpecificationClient extends TmfClient
    <BillingCycleSpecificationCreate, BillingCycleSpecificationUpdate, BillingCycleSpecification> {
}
