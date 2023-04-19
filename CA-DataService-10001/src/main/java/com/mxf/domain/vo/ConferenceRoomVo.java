package com.mxf.domain.vo;

import com.mxf.domain.ConferenceRoomType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author manxingfu
 * @date 2023/1/5
 */
public class ConferenceRoomVo implements Serializable {

    private Integer id;

    @ApiModelProperty(value = "会议室名称")
    private String roomName;

    @ApiModelProperty(value = "会议室类型")
    private ConferenceRoomType roomType;

    @ApiModelProperty(value = "清洁状态")
    private Integer cleanStatus;

    @ApiModelProperty(value = "使用状态")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ConferenceRoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(ConferenceRoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getCleanStatus() {
        return cleanStatus;
    }

    public void setCleanStatus(Integer cleanStatus) {
        this.cleanStatus = cleanStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConferenceRoomVo{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", roomType=" + roomType +
                ", cleanStatus=" + cleanStatus +
                ", status=" + status +
                '}';
    }
}
