package com.csec.goat.service;


import com.csec.goat.entity.AdminWarehouse;

import java.util.List;

public interface AdminWarehouseService {
    /**
     * 保存管理员仓库
     * @param adminWarehouse
     * @return
     */
    boolean saveAdminWarehouse(AdminWarehouse adminWarehouse);

    /**
     * 通过ID获取管理员仓库
     * @param adminId
     * @return
     */
    AdminWarehouse getAdminWarehouseByAdminId(Long adminId);

    /**
     * 批量查询管理员仓库
     * @param idList
     * @return
     */
    List<AdminWarehouse> getAdminWarehouseByAdminIdList(List<Long> idList);

    /**
     * 通过仓库ID查询
     * @param id
     * @return
     */
    List<AdminWarehouse> getAdminWarehouseByWhId(String id);
}
