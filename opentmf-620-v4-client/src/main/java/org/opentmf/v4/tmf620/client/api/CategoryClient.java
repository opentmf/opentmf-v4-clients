package org.opentmf.v4.tmf620.client.api;

import org.opentmf.common.client.api.TmfClient;
import org.opentmf.v4.tmf620.model.Category;
import org.opentmf.v4.tmf620.model.CategoryCreate;
import org.opentmf.v4.tmf620.model.CategoryUpdate;

/**
 * @author Gokhan Demir
 * @see CategoryCreate
 * @see CategoryUpdate
 * @see Category
 */
public interface CategoryClient extends TmfClient
    <CategoryCreate, CategoryUpdate, Category> {
}
