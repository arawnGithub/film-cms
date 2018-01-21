package com.arawn.film.service.impl;

import com.arawn.film.entity.Link;
import com.arawn.film.repository.LinkRepository;
import com.arawn.film.service.LinkService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友情链接Service实现类
 * Created by ArawN on 2018/1/2.
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkRepository linkRepository;

    @Override
    public List<Link> list(Integer page, Integer pageSize) {
        return linkRepository.findAll(new PageRequest(page, pageSize)).getContent();
    }

    @Override
    public List<Link> listAll() {
        return linkRepository.findAll();
    }

    @Override
    public Long count() {
        return linkRepository.count();
    }

    @Override
    public void save(Link link) {
        linkRepository.save(link);
    }

    @Override
    public void delete(Integer id) {
        linkRepository.delete(id);
    }
}
