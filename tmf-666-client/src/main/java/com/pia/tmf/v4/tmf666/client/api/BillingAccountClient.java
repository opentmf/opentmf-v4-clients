package com.pia.tmf.v4.tmf666.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf666.model.BillingAccount;
import com.pia.tmf.v4.tmf666.model.BillingAccountCreate;
import com.pia.tmf.v4.tmf666.model.BillingAccountUpdate;

/**
 * @author Gokhan Demir
 * @see BillingAccountCreate
 * @see BillingAccountUpdate
 * @see BillingAccount
 */
public interface BillingAccountClient extends TmfClient
    <BillingAccountCreate, BillingAccountUpdate, BillingAccount> {
}
