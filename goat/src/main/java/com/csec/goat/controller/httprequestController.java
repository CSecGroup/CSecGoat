package com.csec.goat.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * Created by 格格
 * Date:2018/4/1
 * Copyright by Code Security Group.
 * Description: 发起http请求，ssrf测试
 */
@RestController
@RequestMapping(path = "/http")
public class httprequestController {

    private static Logger logger  =  Logger.getLogger(httprequestController.class);

    @RequestMapping(value="/curl.html",method= RequestMethod.GET)
    public String HttpIndex(ModelMap modelMap, HttpServletRequest request) {
        String url=request.getParameter("url");
        StringBuilder sb=new StringBuilder();
        if(url==null){
            return "url is null!";
        }
        try{
        URL imgurl=new URL(url);
        HttpURLConnection con=(HttpURLConnection)imgurl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent","Mozilla/5.0");
        BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while((line=in.readLine())!=null){
            sb.append(line);
        }
        in.close();
        //return sb.toString();

        }catch(Exception e){
            if(logger.isDebugEnabled()){
                logger.error("HttpIndex request erorr: "+e.toString());
            }
            e.printStackTrace();
        }
        return sb.toString();
    }
    @RequestMapping(value="/curl.html",method= RequestMethod.POST)
    public String HttpPost(ModelMap modelMap, HttpServletRequest request) {
        String url=request.getParameter("url");
        String time=request.getParameter("time");
        StringBuilder sb=new StringBuilder();
        if(url==null){
            return "url is null!";
        }
        try{
            URL imgurl=new URL(url);
            HttpURLConnection con=(HttpURLConnection)imgurl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while((line=in.readLine())!=null){
                sb.append(line);
            }
            in.close();
            //return sb.toString();

        }catch(Exception e){
            if(logger.isDebugEnabled()){
                logger.error("HttpIndex request erorr: "+e.toString());
            }
            e.printStackTrace();
        }
        return sb.toString();
    }
    @RequestMapping(value="/curl.json",method= RequestMethod.POST)
    public String HttpPostJson(@RequestBody String s) {
        logger.info(s);
        return s.toString();
    }
    @RequestMapping(value="/curl.action",method= RequestMethod.POST)
    public String HttpPostognlJson(@RequestBody String s) {
        logger.info(s);
        return s.toString();
    }
    @RequestMapping(value="/ping.html",method= RequestMethod.POST)
    public String Socketping(ModelMap modelMap, HttpServletRequest request) {
        String host=request.getParameter("host");
        String portstr=request.getParameter("port");
        Integer integer = new Integer(portstr);
        int port=integer.parseInt(portstr);
        Socket socket=null;
        if(host==null || portstr==null){
            return "ping info is null!";
        }
        try{
            socket =new Socket(host,port);
            return "ping ok";

        }catch(Exception e){

            e.printStackTrace();
            return "ping false";
        }finally {
            try {
                if(socket!=null){
                    socket.close();
                }
            }catch (IOException e){
                logger.error("socket close error",e);
            }

        }
    }
    @RequestMapping(value="/getimg.html",method= RequestMethod.GET)
    public Image imgread(ModelMap modelMap, HttpServletRequest request) throws IOException {
        String img=request.getParameter("img");
        URL url= null;
        try {
            url = new URL(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image= ImageIO.read(url);
        return image;
    }
}
