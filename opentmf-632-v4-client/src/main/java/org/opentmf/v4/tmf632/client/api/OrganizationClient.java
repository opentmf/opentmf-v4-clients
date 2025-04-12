package org.opentmf.v4.tmf632.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf632.model.Organization;
import org.opentmf.v4.tmf632.model.OrganizationCreate;
import org.opentmf.v4.tmf632.model.OrganizationUpdate;

/**
 * @author Gokhan Demir
 * @see OrganizationCreate
 * @see OrganizationUpdate
 * @see Organization
 */
public interface OrganizationClient extends TmfClient
    <OrganizationCreate, OrganizationUpdate, Organization> {
}
