package com.mxf.common.rabbitmq.dto;

import java.io.Serializable;

/**
 * @author manxingfu
 * @date 2023/4/6
 * @desc: 消息队列传输对象
 */
public class MqMessageDTO<T> implements Serializable {

    /**
     * 用于消息幂等性判断
     */
    private String uuid;
    /**
     * 消息传输携带的实体信息
     */
    private T data;

    public MqMessageDTO() {
    }

    public MqMessageDTO(String uuid, T data) {
        this.uuid = uuid;
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
