package org.opentmf.v4.tmf629.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf629.model.Customer;
import org.opentmf.v4.tmf629.model.CustomerCreate;
import org.opentmf.v4.tmf629.model.CustomerUpdate;

/**
 * @author Gokhan Demir
 * @see CustomerCreate
 * @see CustomerUpdate
 * @see Customer
 */
public interface CustomerClient extends TmfClient
    <CustomerCreate, CustomerUpdate, Customer> {
}
