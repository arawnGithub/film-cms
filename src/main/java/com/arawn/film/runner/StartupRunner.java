package com.arawn.film.runner;

import com.arawn.film.entity.Film;
import com.arawn.film.service.FilmService;
import com.arawn.film.service.LinkService;
import com.arawn.film.service.WebSiteInfoService;
import com.arawn.film.service.WebSiteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

@Component("startupRunner")
public class StartupRunner implements CommandLineRunner, ServletContextAware {

    private ServletContext application;

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private LinkService linkService;

    @Resource
    private WebSiteService webSiteService;

    @Override
    public void run(String... args) throws Exception {
        this.loadData();
    }

    /**
     * 加载数据到application缓存中
     */
    public void loadData() {
        // 最新10条电影动态
        application.setAttribute("newestInfoList", webSiteInfoService.list(null, 0, 10));

        // 获取最新10条热门电影
        Film film = new Film();
        film.setHot(1);
        application.setAttribute("newestHotFilmList", filmService.list(film, 0, 10));

        // 获取最新32条热门电影 首页
        application.setAttribute("newestIndexHotFilmList", filmService.list(film, 0, 32));

        // 获取最新10条电影网站收录
        application.setAttribute("newestWebSiteList", webSiteService.newestList(0, 10));

        // 获取最新10条电影信息
        application.setAttribute("newestFilmList", filmService.list(null, 0, 10));

        // 查询所有友情链接
        application.setAttribute("linkList", linkService.listAll());
    }

    @Override
    public void setServletContext(ServletContext application) {
        this.application = application;
    }

}