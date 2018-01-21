package com.arawn.film.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 网站动态更新电影信息实体
 * Created by ArawN on 2017/12/27.
 */
@Entity
@Table(name = "t_web_site_info")
@Getter
@Setter
public class WebSiteInfo {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 电影
     */
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    /**
     * 网站
     */
    @ManyToOne
    @JoinColumn(name = "web_site_id")
    private WebSite webSite;

    /**
     * 信息
     */
    @Column(length = 500)
    private String info;

    /**
     * 具体网址
     */
    @Column(length = 300)
    private String url;

    /**
     * 发布日期
     */
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date publishDate;
}
