package com.csec.goat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Locale;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cryin
 * Date:2018/3/25
 * Copyright by Code Security Group.
 * Description:CSecGoat漏洞演示首页
 */
@RestController
@RequestMapping(path = "/home")
public class indexController {
    private static Logger logger  =  Logger.getLogger(indexController.class);

    @RequestMapping(value="/index.html",method= RequestMethod.GET)
    public String index(ModelMap modelMap) {
        System.out.println(Locale.getDefault());

        if(logger.isDebugEnabled()){
            logger.info("CSecGoat index page loaded!");
        }
        modelMap.put("hello","Hello CSecGoat!");

        return "Hello CSecGoat!";
    }
    @RequestMapping(value="/showmsg.html",method= RequestMethod.GET)
    public String xsstest(ModelMap modelMap, HttpServletRequest request) {
        String str=request.getParameter("str");

        return str;
    }

}
