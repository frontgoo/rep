package org.frontgoo.scrap.ali.dao.impl;

import org.frontgoo.orm.hibernate.dao.IHibernateDao;
import org.frontgoo.scrap.ali.dao.ItemInfoDao;
import org.frontgoo.scrap.ali.model.ItemInfo;
import org.springframework.stereotype.Repository;

@Repository
public class ItemInfoDaoImpl extends IHibernateDao<ItemInfo, String> implements
		ItemInfoDao {

	public void saveItemInfo(ItemInfo info) {
		save(info);		
	}


	
}
