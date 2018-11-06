package com.csec.goat.controller;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cryin
 * Date:2018/5/6
 * Copyright by Code Security Group.
 * Description: xxe demo
 */
@RestController
@RequestMapping(path = "/xml")
public class XmlReaderController {
    @RequestMapping(value="/parse.html",method= RequestMethod.POST)
    public String xmlPost(ModelMap modelMap, HttpServletRequest request) throws Exception{
        //InputStream in=request.getInputStream();
        String xmldata=request.getParameter("xmldata");
        System.out.println(xmldata);
        String result="";
        if (xmldata==null){
            return "xml data erorr!";
        }
        SAXReader sax=new SAXReader();
        Document document = sax.read(new ByteArrayInputStream(xmldata.getBytes()));
        Element root = document.getRootElement();
        List rowlist = root.selectNodes("//msg");
        Iterator<?> iter1=rowlist.iterator();
        if(iter1.hasNext()){
            Element beanode=(Element)iter1.next();
            result=beanode.getTextTrim();
        }
        /*
            <?xml version="1.0" encoding="UTF-8"?>
            <!DOCTYPE replace [
            <!ENTITY test SYSTEM "file:///Users">]>
            <msg>&test;</msg>
         */
        return result;
    }
}
