package org.opentmf.v4.tmf666.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf666.model.FinancialAccount;
import org.opentmf.v4.tmf666.model.FinancialAccountCreate;
import org.opentmf.v4.tmf666.model.FinancialAccountUpdate;

/**
 * @author Gokhan Demir
 * @see FinancialAccountCreate
 * @see FinancialAccountUpdate
 * @see FinancialAccount
 */
public interface FinancialAccountClient extends TmfClient
    <FinancialAccountCreate, FinancialAccountUpdate, FinancialAccount> {
}
