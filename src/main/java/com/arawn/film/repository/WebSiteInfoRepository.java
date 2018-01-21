package com.arawn.film.repository;

import com.arawn.film.entity.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 电影动态信息Repository
 * Created by ArawN on 2018/1/3.
 */
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Integer>, JpaSpecificationExecutor<WebSiteInfo> {

    /**
     * 根据电影id查询电影动态信息数量
     *
     * @param filmId
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM t_web_site_info WHERE film_id = ?1", nativeQuery = true)
    Long countByFilmId(Integer filmId);

    /**
     * 根据电影网址id查询电影动态信息数量
     *
     * @param webSiteId
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM t_web_site_info WHERE web_site_id = ?1", nativeQuery = true)
    Long countByWebSiteId(Integer webSiteId);

    /**
     * 根据电影id查询电影动态信息
     * @param filmId
     * @return
     */
    @Query(value = "SELECT * FROM t_web_site_info WHERE film_id = ?1", nativeQuery = true)
    List<WebSiteInfo> listByFilmId(Integer filmId);
}
