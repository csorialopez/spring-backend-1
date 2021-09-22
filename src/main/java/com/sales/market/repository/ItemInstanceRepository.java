/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.model.ItemInstance;

import java.util.List;

public interface ItemInstanceRepository extends GenericRepository<ItemInstance> {
    ItemInstance findByIdentifier(String identifier);
    List<ItemInstance> findAllByItem_Id(Long id);
}
