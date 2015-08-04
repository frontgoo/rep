package org.frontgoo.scrap.ali.service;

import org.frontgoo.orm.hibernate.base.GenericService;
import org.frontgoo.scrap.ali.model.ItemInfo;

public interface ItemInfoService extends GenericService<ItemInfo, String>{

	public void saveItemInfo(ItemInfo info);
}
