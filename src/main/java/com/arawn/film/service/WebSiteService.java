package com.arawn.film.service;

import com.arawn.film.entity.WebSite;

import java.util.List;

/**
 * 网址信息Service接口
 * Created by ArawN on 2018/1/3.
 */
public interface WebSiteService {

    /**
     * 分页查询网址信息
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<WebSite> list(WebSite webSite, Integer page, Integer pageSize);

    /**
     * 获取最新收录网址
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<WebSite> newestList(Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @return
     */
    Long count(WebSite webSite);

    /**
     * 添加或者修改网址信息
     *
     * @param webSite
     */
    void save(WebSite webSite);

    /**
     * 根据id删除网址信息
     *
     * @param id
     */
    void delete(Integer id);
}
