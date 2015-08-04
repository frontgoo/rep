package org.frontgoo.scrap.ali.service.impl;

import javax.transaction.Transactional;

import org.frontgoo.orm.hibernate.base.GenericServiceImpl;
import org.frontgoo.scrap.ali.model.ItemInfo;
import org.frontgoo.scrap.ali.service.ItemInfoService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ItemInfoServiceImpl extends GenericServiceImpl<ItemInfo, String> implements ItemInfoService{
	
//	@Autowired
//	ItemInfoDao infoDao;

	@Override
	public void saveItemInfo(ItemInfo info) {
		save(info);
	}

}
