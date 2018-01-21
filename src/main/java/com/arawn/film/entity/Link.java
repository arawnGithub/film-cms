package com.arawn.film.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 友情链接实体
 * Created by ArawN on 2017/12/27.
 */
@Entity
@Table(name = "t_link")
@Getter
@Setter
public class Link {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 名称
     */
    @Column(length = 100)
    private String name;

    /**
     * url地址
     */
    @Column(length = 300)
    private String url;

    /**
     * 排列序号
     */
    private Integer sort;
}
