package com.arawn.film.controller;

import com.arawn.film.entity.Film;
import com.arawn.film.service.FilmService;
import com.arawn.film.service.WebSiteInfoService;
import com.arawn.film.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 电影Controller
 * Created by ArawN on 2017/12/27.
 */
@Controller
@RequestMapping("/film")
public class FilmController {

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 搜索电影 简单模糊查询
     *
     * @param sFilm
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    public ModelAndView search(@Valid Film sFilm, BindingResult bindingResult) throws Exception {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("error", bindingResult.getFieldError().getDefaultMessage());
            mav.addObject("title", "首页");
            mav.addObject("mainPage", "film/indexFilm");
            mav.addObject("mainPageKey", "#f");
            mav.setViewName("index");
        } else {
            List<Film> filmList = filmService.list(sFilm, 0, 32);
            mav.addObject("filmList", filmList);
            mav.addObject("title", sFilm.getName());
            mav.addObject("mainPage", "film/result");
            mav.addObject("mainPageKey", "#f");
            mav.addObject("sFilm", sFilm);
            mav.addObject("total", filmList.size());
            mav.setViewName("index");
        }
        return mav;
    }

    /**
     * 分页查询电影信息
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/{page}")
    public ModelAndView list(@PathVariable("page") Integer page) throws Exception {
        ModelAndView mav = new ModelAndView();

        List<Film> filmList = filmService.list(null, page - 1, 20);
        Long total = filmService.count(null);
        mav.addObject("filmList", filmList);
        mav.addObject("pageCode", PageUtil.genPagination("/film/list", total.intValue(), page, 20));
        mav.addObject("title", "电影列表");
        mav.addObject("mainPage", "film/list");
        mav.addObject("mainPageKey", "#f");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 根据id获取电影详细信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Film film = filmService.findById(id);

        mav.addObject("film", film);
        mav.addObject("title", film.getTitle());
        mav.addObject("randomFilmList", filmService.listByRand(8));
        mav.addObject("webSiteInfoList", webSiteInfoService.listByFilmId(id));
        mav.addObject("pageCode", this.getUpAndDownPageCode(filmService.getLast(id), filmService.getNext(id)));
        mav.addObject("mainPage", "film/view");
        mav.addObject("mainPageKey", "#f");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 获取下一个电影和上一个电影
     *
     * @param lastFilm
     * @param nextFilm
     * @return
     */
    private String getUpAndDownPageCode(Film lastFilm, Film nextFilm) {
        StringBuffer pageCode = new StringBuffer();
        if (lastFilm == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='/film/" + lastFilm.getId() + "'>" + lastFilm.getTitle() + "</a></p>");
        }
        if (nextFilm == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='/film/" + nextFilm.getId() + "'>" + nextFilm.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }

}