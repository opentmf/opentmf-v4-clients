package org.opentmf.v4.tmf666.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf666.model.PartyAccount;
import org.opentmf.v4.tmf666.model.PartyAccountCreate;
import org.opentmf.v4.tmf666.model.PartyAccountUpdate;

/**
 * @author Gokhan Demir
 * @see PartyAccountCreate
 * @see PartyAccountUpdate
 * @see PartyAccount
 */
public interface PartyAccountClient extends TmfClient
    <PartyAccountCreate, PartyAccountUpdate, PartyAccount> {
}
