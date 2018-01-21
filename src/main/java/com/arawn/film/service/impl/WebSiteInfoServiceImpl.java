package com.arawn.film.service.impl;

import com.arawn.film.entity.WebSiteInfo;
import com.arawn.film.repository.WebSiteInfoRepository;
import com.arawn.film.service.WebSiteInfoService;
import com.arawn.film.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 电影动态信息Service实现类
 * Created by ArawN on 2018/1/4.
 */
@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    @Resource
    private WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "publishDate");
        Page<WebSiteInfo> pageWebSite = webSiteInfoRepository.findAll(new Specification<WebSiteInfo>() {

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSiteInfo != null) {
                    if (StringUtil.isNotEmpty(webSiteInfo.getInfo())) {
                        predicate.getExpressions().add(cb.like(root.get("info"), "%" + webSiteInfo.getInfo().trim() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return pageWebSite.getContent();
    }

    @Override
    public Long count(WebSiteInfo webSiteInfo) {
        Long count = webSiteInfoRepository.count(new Specification<WebSiteInfo>() {

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSiteInfo != null) {
                    if (StringUtil.isNotEmpty(webSiteInfo.getInfo())) {
                        predicate.getExpressions().add(cb.like(root.get("info"), "%" + webSiteInfo.getInfo().trim() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public Long countByFilmId(Integer filmId) {
        return webSiteInfoRepository.countByFilmId(filmId);
    }

    @Override
    public Long countByWebSiteId(Integer webSiteId) {
        return webSiteInfoRepository.countByWebSiteId(webSiteId);
    }

    @Override
    public void save(WebSiteInfo webSiteInfo) {
        webSiteInfoRepository.save(webSiteInfo);
    }

    @Override
    public void delete(Integer id) {
        webSiteInfoRepository.delete(id);
    }

    @Override
    public List<WebSiteInfo> listByFilmId(Integer filmId) {
        return webSiteInfoRepository.listByFilmId(filmId);
    }

}
