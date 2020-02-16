package com.ch.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultEntity {
    private Integer status;//0：失败 1：成功
    private String message;
    private Object data;

    public static ResultEntity success() {
        return new ResultEntity(1, "成功", null);
    }

    public static ResultEntity success(Object data) {
        return new ResultEntity(1, "成功", data);
    }

    public static ResultEntity fail(String message) {
        return new ResultEntity(0, message, null);
    }

    public static ResultEntity build(Integer status, String message, Object data) {
        return new ResultEntity(status, message, data);
    }


}
