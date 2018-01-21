package com.arawn.film.repository;

import com.arawn.film.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 电影Repository
 * Created by ArawN on 2018/1/1.
 */
public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {

    /**
     * 获取上一个电影
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM t_film WHERE id < ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Film getLast(Integer id);

    /**
     * 获取下一个电影
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM t_film WHERE id > ?1 ORDER BY id ASC LIMIT 1", nativeQuery = true)
    Film getNext(Integer id);

    /**
     * 随机获取n个电影
     *
     * @param n
     * @return
     */
    @Query(value = "SELECT * FROM t_film ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Film> listByRand(Integer n);
}
