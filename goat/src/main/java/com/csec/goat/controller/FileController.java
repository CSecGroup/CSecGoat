package com.csec.goat.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by cryin
 * Date:2018/5/6
 * Copyright by Code Security Group.
 * Description: wenjian漏洞演示
 */
@RestController
@RequestMapping(path = "/file")
public class FileController {
    @RequestMapping(value="/goto.html",method= RequestMethod.GET)
    public String urlgo(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename=request.getParameter("filenname");
        String filepath="/Users/"+filename;
        if(!filename.isEmpty())
        {
            response.setHeader("content-disposition","attachment;filename="+filename);
            InputStream in=new FileInputStream(filepath);

            int len=0;
            byte[] buffer=new byte[1024];
            OutputStream out=response.getOutputStream();
            while((len=in.read(buffer))>0){
                try{
                    out.write(buffer,0,len);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            in.close();
            out.close();

        }
        return response.toString();
    }
}
