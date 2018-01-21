package com.arawn.film.controller;

import com.arawn.film.entity.WebSiteInfo;
import com.arawn.film.service.WebSiteInfoService;
import com.arawn.film.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电影网站动态信息Controller
 * Created by ArawN on 2018/1/6.
 */
@Controller
@RequestMapping("/webSiteInfo")
public class WebSiteInfoController {

    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 分页查询电影网站动态信息
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/{page}")
    public ModelAndView list(@PathVariable("page") Integer page) throws Exception {
        ModelAndView mav = new ModelAndView();

        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(null, page - 1, 20);
        Long total = webSiteInfoService.count(null);
        mav.addObject("webSiteInfoList", webSiteInfoList);
        mav.addObject("pageCode", PageUtil.genPagination("/webSiteInfo/list", total.intValue(), page, 20));
        mav.addObject("title", "电影网站动态信息列表");
        mav.addObject("mainPage", "webSiteInfo/list");
        mav.addObject("mainPageKey", "#f");
        mav.setViewName("index");
        return mav;
    }

}