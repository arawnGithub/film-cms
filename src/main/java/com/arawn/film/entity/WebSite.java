package com.arawn.film.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 电影网站实体
 * Created by ArawN on 2017/12/27.
 */
@Entity
@Table(name = "t_web_site")
@Getter
@Setter
public class WebSite {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 网站地址
     */
    @Column(length = 300)
    private String url;

    /**
     * 网站名称
     */
    @Column(length = 100)
    private String name;
}
