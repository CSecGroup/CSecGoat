package com.csec.goat.controller;

import com.csec.goat.entity.AdminWarehouse;
import com.csec.goat.service.AdminWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mickle
 * Date:2017/3/25
 * Copyright by Code Security Group.
 * Description:sql注入漏洞演示示例
 */

@RestController
@RequestMapping(path = "/admin")
public class AdminWarehouseController {

    @Autowired
    private AdminWarehouseService adminWarehouseService;

    @RequestMapping(path = "/add")
    public String add(@ModelAttribute AdminWarehouse aw){
        adminWarehouseService.saveAdminWarehouse(aw);
        return "";
    }

    @RequestMapping(path = "/view.html")
    public AdminWarehouse view(Long id){
        return adminWarehouseService.getAdminWarehouseByAdminId(id);
    }

    @RequestMapping(path = "/get.html")
    public List<AdminWarehouse> get(String whId){
        return adminWarehouseService.getAdminWarehouseByWhId(whId);
    }

    @RequestMapping(path = "/add_body")
    public String addBody(@RequestBody AdminWarehouse aw){
        adminWarehouseService.saveAdminWarehouse(aw);
        return "";
    }
}
