package com.arawn.film.controller.admin;

import com.arawn.film.entity.WebSite;
import com.arawn.film.runner.StartupRunner;
import com.arawn.film.service.WebSiteInfoService;
import com.arawn.film.service.WebSiteService;
import com.arawn.film.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网址信息Controller
 * Created by ArawN on 2018/1/3.
 */
@RestController
@RequestMapping("/admin/webSite")
public class WebSiteAdminController {

    @Resource
    private WebSiteService webSiteService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private StartupRunner startupRunner;

    /**
     * 分页查询网址信息
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(WebSite webSite, @RequestParam("page") Integer page, @RequestParam("rows") Integer rows) throws Exception {
        // 获取列表数据和总记录数
        List<WebSite> WebSiteList = webSiteService.list(webSite, page - 1, rows);
        Long total = webSiteService.count(webSite);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", WebSiteList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或者修改网址信息
     *
     * @param webSite
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String, Object> save(WebSite webSite) throws Exception {
        webSiteService.save(webSite);

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除网址信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam("ids") String ids) throws Exception {
        boolean flag = true;

        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            Integer webSiteId = Integer.parseInt(idsStr[i]);

            Long count = webSiteInfoService.countByWebSiteId(webSiteId);
            if (count != null && count > 0) {
                flag = false;
            } else {
                webSiteService.delete(webSiteId);
            }
        }

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> resultMap = new HashMap<>();
        if (flag) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "电影动态信息中存在电影网址信息，不能删除！");
        }
        return resultMap;
    }

    /**
     * 下拉框模糊查询用到
     *
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/comboList")
    public List<WebSite> comboList(String q) throws Exception {
        if (StringUtil.isEmpty(q)) {
            return null;
        }
        WebSite webSite = new WebSite();
        webSite.setUrl(q);
        return webSiteService.list(webSite, 0, 30); // 最多查询30条记录
    }

}
