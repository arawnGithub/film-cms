package com.arawn.film.repository;

import com.arawn.film.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 友情链接Repository
 * Created by ArawN on 2018/1/2.
 */
public interface LinkRepository extends JpaRepository<Link, Integer> {
}
