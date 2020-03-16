package com.raymondmuzzi.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author raymondmuzzi
 * @date 2020-03-15 22:39:58
 */
@Controller
public class MainController {
    /**
     * Forward to the main page
     *
     * @return
     */
    @RequestMapping("main")
    public String main() {
        return "main";
    }
}
