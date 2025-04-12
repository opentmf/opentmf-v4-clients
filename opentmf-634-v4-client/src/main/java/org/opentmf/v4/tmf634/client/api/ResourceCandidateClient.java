package org.opentmf.v4.tmf634.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf634.model.ResourceCandidate;
import org.opentmf.v4.tmf634.model.ResourceCandidateCreate;
import org.opentmf.v4.tmf634.model.ResourceCandidateUpdate;

/**
 * @author Gokhan Demir
 * @see ResourceCandidateCreate
 * @see ResourceCandidateUpdate
 * @see ResourceCandidate
 */
public interface ResourceCandidateClient extends TmfClient
    <ResourceCandidateCreate, ResourceCandidateUpdate, ResourceCandidate> {
}
