package com.example.gisma.enumaration;

public enum OrderStatus {
    PEDNING(1, "PENDING"), ACCEPTED(2, "ACCEPTED"), INPROGRESS(3, "INPROGRESS"), DELIVERED(3, "DELIVERED"),
    CANCELLED(3, "CANCELLED");

    private final Integer code;
    private String statusStr;

    OrderStatus(Integer code, String statusStr) {
        this.code = code;
        this.statusStr = statusStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public Integer getCode() {
        return code;
    }

}
