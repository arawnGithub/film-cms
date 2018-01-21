package com.arawn.film.controller.admin;

import com.arawn.film.entity.Link;
import com.arawn.film.runner.StartupRunner;
import com.arawn.film.service.LinkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 友情链接后台Controller
 * Created by ArawN on 2018/1/2.
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    @Resource
    private StartupRunner startupRunner;

    /**
     * 分页查询友情链接
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows) throws Exception {
        // 由于JPA分页的页码是从0开始，而页面传的页码是从1开始，所以这里需要减一
        List<Link> linkList = linkService.list(page - 1, rows);
        Long total = linkService.count();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", linkList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或者修改友情链接
     *
     * @param link
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String, Object> save(Link link) throws Exception {
        linkService.save(link);

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除友情链接
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam("ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            linkService.delete(Integer.parseInt(idsStr[i]));
        }

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

}