package com.arawn.film.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台页面跳转Controller
 * Created by ArawN on 2017/12/31.
 */
@Controller
@RequestMapping("/admin")
public class PageAdminController {

    @RequestMapping("/{page}")
    public String showPage(@PathVariable("page") String page) {
        return "admin/" + page;
    }
}
