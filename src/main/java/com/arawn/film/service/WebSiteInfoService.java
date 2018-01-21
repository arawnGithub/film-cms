package com.arawn.film.service;

import com.arawn.film.entity.WebSiteInfo;

import java.util.List;

/**
 * 电影动态信息Service接口
 * Created by ArawN on 2018/1/4.
 */
public interface WebSiteInfoService {

    /**
     * 分页查询电影动态信息
     *
     * @param webSiteInfo
     * @param page
     * @param pageSize
     * @return
     */
    List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @param webSiteInfo
     * @return
     */
    Long count(WebSiteInfo webSiteInfo);

    /**
     * 根据电影id查询电影动态信息数量
     *
     * @param filmId
     * @return
     */
    Long countByFilmId(Integer filmId);

    /**
     * 根据电影网址id查询电影动态信息数量
     *
     * @param webSiteId
     * @return
     */
    Long countByWebSiteId(Integer webSiteId);

    /**
     * 添加或者修改电影动态信息
     *
     * @param webSiteInfo
     */
    void save(WebSiteInfo webSiteInfo);

    /**
     * 根据id删除电影动态信息
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据电影id查询电影动态信息
     *
     * @param filmId
     * @return
     */
    List<WebSiteInfo> listByFilmId(Integer filmId);
}
