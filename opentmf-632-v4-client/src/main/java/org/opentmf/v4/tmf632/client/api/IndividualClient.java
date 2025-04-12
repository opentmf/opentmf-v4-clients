package org.opentmf.v4.tmf632.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf632.model.Individual;
import org.opentmf.v4.tmf632.model.IndividualCreate;
import org.opentmf.v4.tmf632.model.IndividualUpdate;

/**
 * @author Gokhan Demir
 * @see IndividualCreate
 * @see IndividualUpdate
 * @see Individual
 */
public interface IndividualClient extends TmfClient
    <IndividualCreate, IndividualUpdate, Individual> {
}
