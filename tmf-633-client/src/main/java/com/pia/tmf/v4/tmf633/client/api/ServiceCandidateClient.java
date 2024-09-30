package com.pia.tmf.v4.tmf633.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf633.model.ServiceCandidate;
import com.pia.tmf.v4.tmf633.model.ServiceCandidateCreate;
import com.pia.tmf.v4.tmf633.model.ServiceCandidateUpdate;

/**
 * @author Gokhan Demir
 * @see ServiceCandidateCreate
 * @see ServiceCandidateUpdate
 * @see ServiceCandidate
 */
public interface ServiceCandidateClient extends TmfClient
    <ServiceCandidateCreate, ServiceCandidateUpdate, ServiceCandidate> {
}
