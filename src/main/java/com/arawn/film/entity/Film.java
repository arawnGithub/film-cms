package com.arawn.film.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * 电影实体
 * Created by ArawN on 2017/12/27.
 */
@Entity
@Table(name = "t_film")
@Getter
@Setter
public class Film {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 电影名称
     */
    @NotEmpty(message="请输入您要搜索的电影！")
    @Column(length = 200)
    private String name;

    /**
     * 帖子名称
     */
    @Column(length = 500)
    private String title;

    /**
     * 帖子内容
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 电影图片地址
     */
    @Column(length = 300)
    private String imagePath;

    /**
     * 是否热门电影 1-热门 0-非热门
     */
    private Integer hot;

    /**
     * 发布日期
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date publishDate;
}
