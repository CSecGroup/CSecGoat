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
 * Date:2018/5/6
 * Copyright by Code Security Group.
 * Description: 重定向demo
 */
@RestController
public class RedirectController {
    @RequestMapping(value="/goto.html",method= RequestMethod.GET)
    public String urlgo(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url=request.getParameter("url");
        if(!url.isEmpty())
        {
            response.sendRedirect(url);
        }
        return url;
    }
}
