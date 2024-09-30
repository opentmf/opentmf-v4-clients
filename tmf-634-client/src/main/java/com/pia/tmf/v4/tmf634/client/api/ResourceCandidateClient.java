package com.pia.tmf.v4.tmf634.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf634.model.ResourceCandidate;
import com.pia.tmf.v4.tmf634.model.ResourceCandidateCreate;
import com.pia.tmf.v4.tmf634.model.ResourceCandidateUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCandidateCreate
 * @see ResourceCandidateUpdate
 * @see ResourceCandidate
 */
public interface ResourceCandidateClient extends TmfClient
    <ResourceCandidateCreate, ResourceCandidateUpdate, ResourceCandidate> {
}
