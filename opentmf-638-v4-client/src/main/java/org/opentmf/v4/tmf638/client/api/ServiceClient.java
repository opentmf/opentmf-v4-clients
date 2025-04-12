package org.opentmf.v4.tmf638.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.service.model.Service;
import org.opentmf.v4.service.model.ServiceCreate;
import org.opentmf.v4.service.model.ServiceUpdate;

/**
 * The ServiceInventoryClient interface defines the contract for interacting with a
 * TMF service inventory system.
 *
 * @author Cezmi Aslan
 * @see ServiceCreate
 * @see ServiceUpdate
 * @see Service
 */
public interface ServiceClient extends TmfClient
    <ServiceCreate, ServiceUpdate, Service> {
}
