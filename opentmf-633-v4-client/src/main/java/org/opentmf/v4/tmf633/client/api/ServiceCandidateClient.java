package org.opentmf.v4.tmf633.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf633.model.ServiceCandidate;
import org.opentmf.v4.tmf633.model.ServiceCandidateCreate;
import org.opentmf.v4.tmf633.model.ServiceCandidateUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceCandidateCreate
 * @see ServiceCandidateUpdate
 * @see ServiceCandidate
 */
public interface ServiceCandidateClient extends TmfClient
    <ServiceCandidateCreate, ServiceCandidateUpdate, ServiceCandidate> {
}
