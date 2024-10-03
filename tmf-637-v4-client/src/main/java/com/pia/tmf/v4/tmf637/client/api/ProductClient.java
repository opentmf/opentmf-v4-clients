package com.pia.tmf.v4.tmf637.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.product.model.Product;
import com.pia.tmf.v4.product.model.ProductCreate;
import com.pia.tmf.v4.product.model.ProductUpdate;

/**
 * @author Gokhan Demir
 * @see ProductCreate
 * @see ProductUpdate
 * @see Product
 */
public interface ProductClient extends TmfClient
    <ProductCreate, ProductUpdate, Product> {
}
