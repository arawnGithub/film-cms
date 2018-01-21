package com.arawn.film.repository;

import com.arawn.film.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 网址信息Repository
 * Created by ArawN on 2018/1/3.
 */
public interface WebSiteRepository extends JpaRepository<WebSite, Integer>, JpaSpecificationExecutor<WebSite> {
}
