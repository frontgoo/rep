package org.frontgoo.scrap.ali.controller;

import org.frontgoo.scrap.ali.model.ItemInfo;
import org.frontgoo.scrap.ali.service.ItemInfoService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemInfoService infoService;
	@Autowired
	private SessionFactory sessionFactory;
	
	LocalSessionFactoryBean se;
	
    @RequestMapping(value="/query/test.action",method=RequestMethod.GET)
    public ModelAndView hello2(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("test");
        ItemInfo info = new ItemInfo();
        info.setTitle("test");
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
