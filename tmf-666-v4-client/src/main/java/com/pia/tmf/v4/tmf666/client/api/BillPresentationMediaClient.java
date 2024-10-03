package com.pia.tmf.v4.tmf666.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf666.model.BillPresentationMedia;
import com.pia.tmf.v4.tmf666.model.BillPresentationMediaCreate;
import com.pia.tmf.v4.tmf666.model.BillPresentationMediaUpdate;

/**
 * @author Gokhan Demir
 * @see BillPresentationMediaCreate
 * @see BillPresentationMediaUpdate
 * @see BillPresentationMedia
 */
public interface BillPresentationMediaClient extends TmfClient
    <BillPresentationMediaCreate, BillPresentationMediaUpdate, BillPresentationMedia> {
}
