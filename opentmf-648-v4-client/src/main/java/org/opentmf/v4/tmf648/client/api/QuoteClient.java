package org.opentmf.v4.tmf648.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf648.model.Quote;
import org.opentmf.v4.tmf648.model.QuoteCreate;
import org.opentmf.v4.tmf648.model.QuoteUpdate;

/**
 * @author Gokhan Demir
 * @see QuoteCreate
 * @see QuoteUpdate
 * @see Quote
 */
public interface QuoteClient
    extends TmfClient<QuoteCreate, QuoteUpdate, Quote> {
}
