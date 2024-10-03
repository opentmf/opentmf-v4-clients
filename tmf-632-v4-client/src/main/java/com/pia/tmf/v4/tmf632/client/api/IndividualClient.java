package com.pia.tmf.v4.tmf632.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf632.model.Individual;
import com.pia.tmf.v4.tmf632.model.IndividualCreate;
import com.pia.tmf.v4.tmf632.model.IndividualUpdate;

/**
 * @author Gokhan Demir
 * @see IndividualCreate
 * @see IndividualUpdate
 * @see Individual
 */
public interface IndividualClient extends TmfClient
    <IndividualCreate, IndividualUpdate, Individual> {
}
