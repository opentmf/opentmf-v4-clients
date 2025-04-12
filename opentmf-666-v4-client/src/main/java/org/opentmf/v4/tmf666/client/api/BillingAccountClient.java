package org.opentmf.v4.tmf666.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf666.model.BillingAccount;
import org.opentmf.v4.tmf666.model.BillingAccountCreate;
import org.opentmf.v4.tmf666.model.BillingAccountUpdate;

/**
 * @author Gokhan Demir
 * @see BillingAccountCreate
 * @see BillingAccountUpdate
 * @see BillingAccount
 */
public interface BillingAccountClient extends TmfClient
    <BillingAccountCreate, BillingAccountUpdate, BillingAccount> {
}
