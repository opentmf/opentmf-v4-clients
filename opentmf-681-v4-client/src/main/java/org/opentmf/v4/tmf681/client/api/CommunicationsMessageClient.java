package org.opentmf.v4.tmf681.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf681.model.CommunicationMessage;
import org.opentmf.v4.tmf681.model.CommunicationMessageCreate;
import org.opentmf.v4.tmf681.model.CommunicationMessageUpdate;

/**
 * @author Gokhan Demir
 * @see CommunicationMessageCreate
 * @see CommunicationMessageUpdate
 * @see CommunicationMessage
 */
public interface CommunicationsMessageClient extends TmfClient
    <CommunicationMessageCreate, CommunicationMessageUpdate, CommunicationMessage> {
}
