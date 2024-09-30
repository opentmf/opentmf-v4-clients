package com.pia.tmf.v4.tmf666.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf666.model.SettlementAccount;
import com.pia.tmf.v4.tmf666.model.SettlementAccountCreate;
import com.pia.tmf.v4.tmf666.model.SettlementAccountUpdate;

/**
 * @author Gokhan Demir
 * @see SettlementAccountCreate
 * @see SettlementAccountUpdate
 * @see SettlementAccount
 */
public interface SettlementAccountClient extends TmfClient
    <SettlementAccountCreate, SettlementAccountUpdate, SettlementAccount> {
}
