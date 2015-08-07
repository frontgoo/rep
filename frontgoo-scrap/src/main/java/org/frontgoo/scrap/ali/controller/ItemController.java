package org.frontgoo.scrap.ali.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.frontgoo.scrap.ali.model.ItemInfo;
import org.frontgoo.scrap.ali.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemInfoService infoService;
	
	LocalSessionFactoryBean se;
	
    @RequestMapping(value="/query",method=RequestMethod.GET)
    @Transactional
    public ModelAndView hello2(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("test");
        ItemInfo info = new ItemInfo();
        info.setTitle("test");
        info.setId(RandomStringUtils.randomAlphanumeric(32));
        infoService.saveItemInfo(info);
        return mv;
    }
    
    @RequestMapping(value="/count",method=RequestMethod.GET)
    public ModelAndView count(){
        
//        int c = service.userCount();
        
        ModelAndView mv = new ModelAndView();
//        mv.addObject("message", c);
        mv.setViewName("users");
        return mv;
    }
}
