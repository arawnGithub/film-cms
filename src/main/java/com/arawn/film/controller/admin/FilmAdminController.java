package com.arawn.film.controller.admin;

import com.arawn.film.entity.Film;
import com.arawn.film.runner.StartupRunner;
import com.arawn.film.service.FilmService;
import com.arawn.film.service.WebSiteInfoService;
import com.arawn.film.util.DateUtil;
import com.arawn.film.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影后台Controller
 * Created by ArawN on 2017/12/31.
 */
@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private StartupRunner startupRunner;

    @Value("${imageFilePath}")
    private String imageFilePath;

    @Value("${imageFileUrl}")
    private String imageFileUrl;

    /**
     * ckeditor图片文件上传
     *
     * @param file
     * @param CKEditorFuncNum
     * @return
     * @throws Exception
     */
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 获取实际存放的文件名
        String newFileName = DateUtil.getCurrentDateStr() + suffixName;

        // 将上传的文件拷到本地目录
        FileUtils.copyInputStreamToFile(file.getInputStream(),
                new File(imageFilePath, newFileName));

        // 传给ckeditor，用于回显的URL
        String callbackUrl = imageFileUrl + newFileName;

        StringBuilder script = new StringBuilder();
        script.append("<script type=\"text/javascript\">");
        script.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + callbackUrl + "','')");
        script.append("</script>");

        return script.toString();
    }

    /**
     * 添加或者修改电影信息
     *
     * @param file
     * @param film
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String, Object> save(@RequestParam("imageFile") MultipartFile file, Film film) throws Exception {
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 获取实际存放的文件名
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;

            // 将上传的文件拷到本地目录
            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File(imageFilePath, newFileName));

            film.setImagePath(imageFileUrl + newFileName);
        }
        film.setPublishDate(new Date());
        filmService.save(film);

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * 分页查询电影信息
     *
     * @param film
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Film film, @RequestParam("page") Integer page, @RequestParam("rows") Integer rows) throws Exception {
        List<Film> filmList = filmService.list(film, page - 1, rows);
        Long total = filmService.count(film);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", filmList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 删除电影信息
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
            Integer filmId = Integer.parseInt(idsStr[i]);

            Long count = webSiteInfoService.countByFilmId(filmId);
            if (count != null && count > 0) {
                flag = false;
            } else {
                filmService.deleteById(filmId);
            }
        }

        // 刷新缓存
        startupRunner.loadData();

        Map<String, Object> resultMap = new HashMap<>();
        if (flag) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "电影动态信息中存在电影信息，不能删除！");
        }
        return resultMap;
    }

    /**
     * 根据id查找实体
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public Film findById(@RequestParam("id") Integer id) throws Exception {
        return filmService.findById(id);
    }

    /**
     * 下拉框模糊查询用到
     *
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/comboList")
    public List<Film> comboList(String q) throws Exception {
        if (StringUtil.isEmpty(q)) {
            return null;
        }
        Film film = new Film();
        film.setName(q);
        return filmService.list(film, 0, 30); // 最多查询30条记录
    }

}