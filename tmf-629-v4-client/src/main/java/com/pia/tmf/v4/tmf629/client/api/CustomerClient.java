package com.pia.tmf.v4.tmf629.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf629.model.Customer;
import com.pia.tmf.v4.tmf629.model.CustomerCreate;
import com.pia.tmf.v4.tmf629.model.CustomerUpdate;

/**
 * @author Gokhan Demir
 * @see CustomerCreate
 * @see CustomerUpdate
 * @see Customer
 */
public interface CustomerClient extends TmfClient
    <CustomerCreate, CustomerUpdate, Customer> {
}
