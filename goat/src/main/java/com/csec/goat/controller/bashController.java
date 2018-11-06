package com.csec.goat.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 格格
 * Date:2017/3/25
 * Copyright by Code Security Group.
 * Description:命令执行漏洞演示示例
 */
@RestController
public class bashController {
    private static Logger logger  =  Logger.getLogger(bashController.class);

    @RequestMapping(value="/bash.html",method= RequestMethod.GET)
    public String bashIndex(ModelMap modelMap, HttpServletRequest request) {
        String cmd=request.getParameter("cmd");
        String result="";
        try{

            Process proc=Runtime.getRuntime().exec(cmd);
            proc.waitFor();

            result=converterCompileInfo(proc.getInputStream());
        }catch(Exception e){
            if(logger.isDebugEnabled()){
                logger.error("bashIndex request erorr: "+e.toString());
            }
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping(value="/bash",method= RequestMethod.POST)
    public String bashPost(ModelMap modelMap, HttpServletRequest request) {
        String cmd=request.getParameter("cmd");
        List<String> commands=new ArrayList();
        commands.add(cmd);

        String result="";
        try{

                ProcessBuilder pb = new ProcessBuilder(commands);

                // start the subprocess
                System.out.println("Starting the process..");
                Process proc=pb.start();
                proc.waitFor();
                result=converterCompileInfo(proc.getInputStream());
            } catch (IOException ex) {
                if(logger.isDebugEnabled()){
                    logger.error("bashpost request erorr: "+ex.toString());
                }
                ex.printStackTrace();
            } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
    public String converterCompileInfo(InputStream inputStream){
        String result="";
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder sb=new StringBuilder();
            String line="";
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            result=sb.toString();
        }catch (Exception e){
            result="Error "+e.toString();
        }
        return result;
    }
}
