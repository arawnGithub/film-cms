package com.arawn.film.service.impl;

import com.arawn.film.entity.Film;
import com.arawn.film.repository.FilmRepository;
import com.arawn.film.service.FilmService;
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
 * 电影Service实现类
 * Created by ArawN on 2018/1/1.
 */
@Service("filmService")
public class FilmServiceImpl implements FilmService {

    @Resource
    private FilmRepository filmRepository;

    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }

    @Override
    public List<Film> list(Film film, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "publishDate");
        Page<Film> pageWebSite = filmRepository.findAll(new Specification<Film>() {

            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (film != null) {
                    if (StringUtil.isNotEmpty(film.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + film.getName().trim() + "%"));
                    }
                    if (film.getHot() != null) {
                        predicate.getExpressions().add(cb.equal(root.get("hot"), film.getHot()));
                    }
                }
                return predicate;
            }
        }, pageable);
        return pageWebSite.getContent();
    }

    @Override
    public Long count(Film film) {
        Long count = filmRepository.count(new Specification<Film>() {

            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (film != null) {
                    if (StringUtil.isNotEmpty(film.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + film.getName().trim() + "%"));
                    }
                    if (film.getHot() != null) {
                        predicate.getExpressions().add(cb.equal(root.get("hot"), film.getHot()));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public Film findById(Integer id) {
        return filmRepository.findOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        filmRepository.delete(id);
    }

    @Override
    public Film getLast(Integer id) {
        return filmRepository.getLast(id);
    }

    @Override
    public Film getNext(Integer id) {
        return filmRepository.getNext(id);
    }

    @Override
    public List<Film> listByRand(Integer n) {
        return filmRepository.listByRand(n);
    }
}
