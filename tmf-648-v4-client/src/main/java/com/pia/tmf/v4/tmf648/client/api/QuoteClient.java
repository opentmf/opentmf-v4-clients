package com.pia.tmf.v4.tmf648.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf648.model.Quote;
import com.pia.tmf.v4.tmf648.model.QuoteCreate;
import com.pia.tmf.v4.tmf648.model.QuoteUpdate;

/**
 * @author Gokhan Demir
 * @see QuoteCreate
 * @see QuoteUpdate
 * @see Quote
 */
public interface QuoteClient
    extends TmfClient<QuoteCreate, QuoteUpdate, Quote> {
}
