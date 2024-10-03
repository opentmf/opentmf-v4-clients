package com.pia.tmf.v4.tmf666.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf666.model.BillFormat;
import com.pia.tmf.v4.tmf666.model.BillFormatCreate;
import com.pia.tmf.v4.tmf666.model.BillFormatUpdate;

/**
 * @author Gokhan Demir
 * @see BillFormatCreate
 * @see BillFormatUpdate
 * @see BillFormat
 */
public interface BillFormatClient extends TmfClient
    <BillFormatCreate, BillFormatUpdate, BillFormat> {
}
