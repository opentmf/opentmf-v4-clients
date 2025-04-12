package org.opentmf.v4.tmf666.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf666.model.SettlementAccount;
import org.opentmf.v4.tmf666.model.SettlementAccountCreate;
import org.opentmf.v4.tmf666.model.SettlementAccountUpdate;

/**
 * @author Gokhan Demir
 * @see SettlementAccountCreate
 * @see SettlementAccountUpdate
 * @see SettlementAccount
 */
public interface SettlementAccountClient extends TmfClient
    <SettlementAccountCreate, SettlementAccountUpdate, SettlementAccount> {
}
