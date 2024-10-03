package com.pia.tmf.v4.tmf620.client.api;

import com.pia.tmf.common.client.api.TmfClient;
import com.pia.tmf.v4.tmf620.model.Category;
import com.pia.tmf.v4.tmf620.model.CategoryCreate;
import com.pia.tmf.v4.tmf620.model.CategoryUpdate;

/**
 * @author Gokhan Demir
 * @see CategoryCreate
 * @see CategoryUpdate
 * @see Category
 */
public interface CategoryClient extends TmfClient
    <CategoryCreate, CategoryUpdate, Category> {
}
