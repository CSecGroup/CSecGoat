package com.csec.goat.util;

import java.io.Serializable;

/**
 * Created by cryin
 * Date:2018/10/7
 * Copyright by Code Security Group.
 * Description: 反序列化测试类
 */
public class astpiastclass implements Serializable {
    private static final long serialVersionUID = 5754104541168322018L;

    private int id;
    public String name;

    public astpiastclass(int id,String name){
        this.id=id;
        this.name=name;
    }
}
