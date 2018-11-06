package com.csec.goat.dao;

import com.csec.goat.entity.AdminWarehouse;

import java.util.List;
public interface AdminWarehouseDao {

    void saveAdminWarehouse(AdminWarehouse adminWarehouse);

    int updateAdminWarehouse(AdminWarehouse adminWarehouse);

    AdminWarehouse getAdminWarehouseByAdminId(Long adminId);

    List<AdminWarehouse> getAdminWarehouseByAdminIdList(List<Long> idList);

    List<AdminWarehouse> getAdminWarehouseByWhId(AdminWarehouse adminWarehouse);

}
