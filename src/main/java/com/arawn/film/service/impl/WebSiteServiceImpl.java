package com.arawn.film.service.impl;

import com.arawn.film.entity.WebSite;
import com.arawn.film.repository.WebSiteRepository;
import com.arawn.film.service.WebSiteService;
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
 * 网址信息Service实现类
 * Created by ArawN on 2018/1/3.
 */
@Service("webSiteService")
public class WebSiteServiceImpl implements WebSiteService {

    @Resource
    private WebSiteRepository webSiteRepository;

    @Override
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");
        Page<WebSite> pageWebSite = webSiteRepository.findAll(new Specification<WebSite>() {

            @Override
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSite != null) {
                    if (StringUtil.isNotEmpty(webSite.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + webSite.getName().trim() + "%"));
                    }
                    if (StringUtil.isNotEmpty(webSite.getUrl())) {
                        predicate.getExpressions().add(cb.like(root.get("url"), "%" + webSite.getUrl().trim() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return pageWebSite.getContent();
    }

    @Override
    public List<WebSite> newestList(Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return webSiteRepository.findAll(pageable).getContent();
    }

    @Override
    public Long count(WebSite webSite) {
        Long count = webSiteRepository.count(new Specification<WebSite>() {

            @Override
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSite != null) {
                    if (StringUtil.isNotEmpty(webSite.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + webSite.getName().trim() + "%"));
                    }
                    if (StringUtil.isNotEmpty(webSite.getUrl())) {
                        predicate.getExpressions().add(cb.like(root.get("url"), "%" + webSite.getUrl().trim() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void save(WebSite webSite) {
        webSiteRepository.save(webSite);
    }

    @Override
    public void delete(Integer id) {
        webSiteRepository.delete(id);
    }

}
