package com.pia.tmf.v4.tmf666.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf666.model.FinancialAccount;
import com.pia.tmf.v4.tmf666.model.FinancialAccountCreate;
import com.pia.tmf.v4.tmf666.model.FinancialAccountUpdate;

/**
 * @author Gokhan Demir
 * @see FinancialAccountCreate
 * @see FinancialAccountUpdate
 * @see FinancialAccount
 */
public interface FinancialAccountClient extends TmfClient
    <FinancialAccountCreate, FinancialAccountUpdate, FinancialAccount> {
}
