package com.pia.tmf.v4.tmf663.client.api;

import com.pia.tmf.v4.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf663.model.ShoppingCart;
import com.pia.tmf.v4.tmf663.model.ShoppingCartCreate;
import com.pia.tmf.v4.tmf663.model.ShoppingCartUpdate;

/**
 * @author Gokhan Demir
 * @see ShoppingCartCreate
 * @see ShoppingCartUpdate
 * @see ShoppingCart
 */
public interface ShoppingCartClient extends TmfClient
    <ShoppingCartCreate, ShoppingCartUpdate, ShoppingCart> {
}
