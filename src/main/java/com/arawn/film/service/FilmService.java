package com.arawn.film.service;

import com.arawn.film.entity.Film;

import java.util.List;

/**
 * 电影Service接口
 * Created by ArawN on 2018/1/1.
 */
public interface FilmService {

    /**
     * 保存电影
     *
     * @param film
     */
    void save(Film film);

    /**
     * 分页查询电影信息
     *
     * @param film
     * @param page
     * @param pageSize
     * @return
     */
    List<Film> list(Film film, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @param film
     * @return
     */
    Long count(Film film);

    /**
     * 根据id查找实体
     *
     * @param id
     * @return
     */
    Film findById(Integer id);

    /**
     * 根据id删除电影
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 获取上一个电影
     *
     * @param id
     * @return
     */
    Film getLast(Integer id);

    /**
     * 获取下一个电影
     *
     * @param id
     * @return
     */
    Film getNext(Integer id);

    /**
     * 随机获取n个电影
     *
     * @param n
     * @return
     */
    List<Film> listByRand(Integer n);
}
