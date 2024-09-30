package com.pia.tmf.v4.tmf632.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf632.model.Organization;
import com.pia.tmf.v4.tmf632.model.OrganizationCreate;
import com.pia.tmf.v4.tmf632.model.OrganizationUpdate;

/**
 * @author Gokhan Demir
 * @see OrganizationCreate
 * @see OrganizationUpdate
 * @see Organization
 */
public interface OrganizationClient extends TmfClient
    <OrganizationCreate, OrganizationUpdate, Organization> {
}
