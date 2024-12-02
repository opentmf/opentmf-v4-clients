package com.pia.tmf.v4.tmf681.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf681.model.CommunicationMessage;
import com.pia.tmf.v4.tmf681.model.CommunicationMessageCreate;
import com.pia.tmf.v4.tmf681.model.CommunicationMessageUpdate;

/**
 * @author Gokhan Demir
 * @see CommunicationMessageCreate
 * @see CommunicationMessageUpdate
 * @see CommunicationMessage
 */
public interface CommunicationsMessageClient extends TmfClient
    <CommunicationMessageCreate, CommunicationMessageUpdate, CommunicationMessage> {
}
