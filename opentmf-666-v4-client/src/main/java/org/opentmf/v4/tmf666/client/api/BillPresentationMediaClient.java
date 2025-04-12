package org.opentmf.v4.tmf666.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf666.model.BillPresentationMedia;
import org.opentmf.v4.tmf666.model.BillPresentationMediaCreate;
import org.opentmf.v4.tmf666.model.BillPresentationMediaUpdate;

/**
 * @author Gokhan Demir
 * @see BillPresentationMediaCreate
 * @see BillPresentationMediaUpdate
 * @see BillPresentationMedia
 */
public interface BillPresentationMediaClient extends TmfClient
    <BillPresentationMediaCreate, BillPresentationMediaUpdate, BillPresentationMedia> {
}
