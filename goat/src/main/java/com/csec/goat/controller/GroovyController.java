package com.csec.goat.controller;

import groovy.lang.GroovyShell;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cryin
 * Date:2018/5/6
 * Copyright by Code Security Group.
 * Description:groovy exec demo
 */
@RestController
@RequestMapping(path = "/groovy")
public class GroovyController {
    private static Logger logger  =  Logger.getLogger(GroovyController.class);

    @RequestMapping(value="/exec.html",method= RequestMethod.POST)
    public String groovyIndex(ModelMap modelMap, HttpServletRequest request) {
        String script=request.getParameter("script");
        Object result="";
        try{

            GroovyShell shell=new GroovyShell();

            result=shell.evaluate(script);
        }catch(Exception e){
            if(logger.isDebugEnabled()){
                logger.error("bashIndex request erorr: "+e.toString());
            }
            e.printStackTrace();
        }
        return result.toString();
    }

}
