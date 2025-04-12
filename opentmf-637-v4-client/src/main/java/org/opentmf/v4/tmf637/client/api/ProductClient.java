package org.opentmf.v4.tmf637.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.product.model.Product;
import org.opentmf.v4.product.model.ProductCreate;
import org.opentmf.v4.product.model.ProductUpdate;

/**
 * @author Gokhan Demir
 * @see ProductCreate
 * @see ProductUpdate
 * @see Product
 */
public interface ProductClient extends TmfClient
    <ProductCreate, ProductUpdate, Product> {
}
