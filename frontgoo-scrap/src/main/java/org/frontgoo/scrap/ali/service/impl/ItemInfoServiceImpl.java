package org.frontgoo.scrap.ali.service.impl;

import org.frontgoo.orm.hibernate.base.GenericServiceImpl;
import org.frontgoo.scrap.ali.dao.ItemInfoDao;
import org.frontgoo.scrap.ali.model.ItemInfo;
import org.frontgoo.scrap.ali.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemInfoServiceImpl extends GenericServiceImpl<ItemInfo, String> implements ItemInfoService{
	
	@Autowired
	@Qualifier("itemInfoDaoImpl")
	ItemInfoDao infoDao;

	public void saveItemInfo(ItemInfo info) {
		infoDao.saveItemInfo(info);
	}

}
