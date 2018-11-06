package com.csec.goat.service.impl;

import com.csec.goat.dao.AdminWarehouseDao;
import com.csec.goat.entity.AdminWarehouse;
import com.csec.goat.service.AdminWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminWarehouseServiceImpl implements AdminWarehouseService {
    @Autowired
    private AdminWarehouseDao adminWarehouseDao;

    @Override
    public boolean saveAdminWarehouse(AdminWarehouse adminWarehouse) {
        AdminWarehouse awh = this.getAdminWarehouseByAdminId(adminWarehouse.getAdminId());
        if(null == awh) {
            adminWarehouseDao.saveAdminWarehouse(adminWarehouse);
        }else{
            adminWarehouse.setId(awh.getId());
            adminWarehouseDao.updateAdminWarehouse(adminWarehouse);
        }
        return null != adminWarehouse.getId();
    }

    @Override
    public AdminWarehouse getAdminWarehouseByAdminId(Long adminId) {
        return adminWarehouseDao.getAdminWarehouseByAdminId(adminId);
    }

    @Override
    public List<AdminWarehouse> getAdminWarehouseByAdminIdList(List<Long> idList) {
        return adminWarehouseDao.getAdminWarehouseByAdminIdList(idList);
    }

    @Override
    public List<AdminWarehouse> getAdminWarehouseByWhId(String whId) {
        return adminWarehouseDao.getAdminWarehouseByWhId(new AdminWarehouse(whId));
    }
}
