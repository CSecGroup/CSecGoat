package com.csec.goat.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cryin
 * Date:2018/10/6
 * Copyright by Code Security Group.
 * Description: webapp banner id test
 */
@RestController
public class WebappBannerController {
    @RequestMapping(value="/robots.txt",method= RequestMethod.GET)
    public String robots(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "robots.txt for Discuz! X2";
    }
}
