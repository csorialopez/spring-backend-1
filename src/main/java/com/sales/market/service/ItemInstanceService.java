/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Buy;
import com.sales.market.model.ItemInstance;

public interface ItemInstanceService extends GenericService<ItemInstance> {
     void saveItemInstances(Buy buy);
     ItemInstance getItemInstanceByIdentifier(String identifier);
}
