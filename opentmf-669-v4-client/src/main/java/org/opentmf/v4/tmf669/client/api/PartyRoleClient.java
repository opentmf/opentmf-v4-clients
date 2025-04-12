package org.opentmf.v4.tmf669.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf669.model.PartyRole;
import org.opentmf.v4.tmf669.model.PartyRoleCreate;
import org.opentmf.v4.tmf669.model.PartyRoleUpdate;

/**
 * @author Gokhan Demir
 * @see PartyRoleCreate
 * @see PartyRoleUpdate
 * @see PartyRole
 */
public interface PartyRoleClient extends TmfClient
    <PartyRoleCreate, PartyRoleUpdate, PartyRole> {
}
