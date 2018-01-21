package com.arawn.film.controller.admin;

import com.arawn.film.entity.WebSiteInfo;
import com.arawn.film.runner.StartupRunner;
import com.arawn.film.service.WebSiteInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影动态信息管理Controller
 * Created by ArawN on 2018/1/4.
 */
@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private StartupRunner startupRunner;

    /**
     * 分页查询电影动态信息
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(WebSiteInfo webSiteInfo, @RequestParam("page") Integer page, @RequestParam("rows") Integer rows) throws Exception {
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(webSiteInfo, page - 1, rows);
        Long total = webSiteInfoService.count(webSiteInfo);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", webSiteInfoList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 删除电影动态信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            webSiteInfoService.delete(Integer.parseInt(idsStr[i]));
        }

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 添加电影动态信息
     * @param webSiteInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String, Object> save(WebSiteInfo webSiteInfo) throws Exception {
        webSiteInfo.setPublishDate(new Date());
        webSiteInfoService.save(webSiteInfo);

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

}
