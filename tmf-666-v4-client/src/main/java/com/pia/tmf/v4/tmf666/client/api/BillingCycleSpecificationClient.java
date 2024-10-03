package com.pia.tmf.v4.tmf666.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf666.model.BillingCycleSpecification;
import com.pia.tmf.v4.tmf666.model.BillingCycleSpecificationCreate;
import com.pia.tmf.v4.tmf666.model.BillingCycleSpecificationUpdate;

/**
 * @author Gokhan Demir
 * @see BillingCycleSpecificationCreate
 * @see BillingCycleSpecificationUpdate
 * @see BillingCycleSpecification
 */
public interface BillingCycleSpecificationClient extends TmfClient
    <BillingCycleSpecificationCreate, BillingCycleSpecificationUpdate, BillingCycleSpecification> {
}
