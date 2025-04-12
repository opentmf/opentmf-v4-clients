package org.opentmf.v4.tmf666.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf666.model.BillFormat;
import org.opentmf.v4.tmf666.model.BillFormatCreate;
import org.opentmf.v4.tmf666.model.BillFormatUpdate;

/**
 * @author Gokhan Demir
 * @see BillFormatCreate
 * @see BillFormatUpdate
 * @see BillFormat
 */
public interface BillFormatClient extends TmfClient
    <BillFormatCreate, BillFormatUpdate, BillFormat> {
}
