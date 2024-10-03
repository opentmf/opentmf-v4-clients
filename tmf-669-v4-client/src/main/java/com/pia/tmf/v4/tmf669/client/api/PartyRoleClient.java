package com.pia.tmf.v4.tmf669.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf669.model.PartyRole;
import com.pia.tmf.v4.tmf669.model.PartyRoleCreate;
import com.pia.tmf.v4.tmf669.model.PartyRoleUpdate;

/**
 * @author Gokhan Demir
 * @see PartyRoleCreate
 * @see PartyRoleUpdate
 * @see PartyRole
 */
public interface PartyRoleClient extends TmfClient
    <PartyRoleCreate, PartyRoleUpdate, PartyRole> {
}
