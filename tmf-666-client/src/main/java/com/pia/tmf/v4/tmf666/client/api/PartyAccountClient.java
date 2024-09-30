package com.pia.tmf.v4.tmf666.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf666.model.PartyAccount;
import com.pia.tmf.v4.tmf666.model.PartyAccountCreate;
import com.pia.tmf.v4.tmf666.model.PartyAccountUpdate;

/**
 * @author Gokhan Demir
 * @see PartyAccountCreate
 * @see PartyAccountUpdate
 * @see PartyAccount
 */
public interface PartyAccountClient extends TmfClient
    <PartyAccountCreate, PartyAccountUpdate, PartyAccount> {
}
