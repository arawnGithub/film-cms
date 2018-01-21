package com.arawn.film.service;

import com.arawn.film.entity.Link;

import java.util.List;

/**
 * 友情链接Service接口
 * Created by ArawN on 2018/1/2.
 */
public interface LinkService {

    /**
     * 分页查询友情链接
     * @param page
     * @param pageSize
     * @return
     */
    List<Link> list(Integer page, Integer pageSize);

    /**
     * 查询所有友情链接
     * @return
     */
    List<Link> listAll();

    /**
     * 获取总记录数
     * @return
     */
    Long count();

    /**
     * 添加或者修改友情链接
     * @param link
     */
    void save(Link link);

    /**
     * 根据id删除友情链接
     * @param id
     */
    void delete(Integer id);
}
