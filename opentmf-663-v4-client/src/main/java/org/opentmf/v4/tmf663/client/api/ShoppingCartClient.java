package org.opentmf.v4.tmf663.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf663.model.ShoppingCart;
import org.opentmf.v4.tmf663.model.ShoppingCartCreate;
import org.opentmf.v4.tmf663.model.ShoppingCartUpdate;

/**
 * @author Gokhan Demir
 * @see ShoppingCartCreate
 * @see ShoppingCartUpdate
 * @see ShoppingCart
 */
public interface ShoppingCartClient extends TmfClient
    <ShoppingCartCreate, ShoppingCartUpdate, ShoppingCart> {
}
