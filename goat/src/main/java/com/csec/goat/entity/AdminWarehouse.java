package com.csec.goat.entity;

import java.io.Serializable;

public class AdminWarehouse implements Serializable{
    private Long id;

    private Long adminId;

    private String warehouseIds;

    private Long operatorId;

    public AdminWarehouse() {
    }

    public AdminWarehouse(String warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getWarehouseIds() {
        return warehouseIds;
    }

    public void setWarehouseIds(String warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
