package com.arawn.film.controller;

import com.arawn.film.entity.WebSite;
import com.arawn.film.service.WebSiteService;
import com.arawn.film.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电影网址Controller
 * Created by ArawN on 2018/1/6.
 */
@Controller
@RequestMapping("/webSite")
public class WebSiteController {

    @Resource
    private WebSiteService webSiteService;

    /**
     * 分页查询电影网址
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/{page}")
    public ModelAndView list(@PathVariable("page") Integer page) throws Exception {
        ModelAndView mav = new ModelAndView();

        List<WebSite> webSiteList = webSiteService.list(null, page - 1, 20);
        Long total = webSiteService.count(null);
        mav.addObject("webSiteList", webSiteList);
        mav.addObject("pageCode", PageUtil.genPagination("/webSite/list", total.intValue(), page, 20));
        mav.addObject("title", "电影网址列表");
        mav.addObject("mainPage", "webSite/list");
        mav.addObject("mainPageKey", "#f");
        mav.setViewName("index");
        return mav;
    }

}