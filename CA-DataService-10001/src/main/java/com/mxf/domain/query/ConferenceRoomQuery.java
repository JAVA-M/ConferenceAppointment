package com.mxf.domain.query;

import java.io.Serializable;

/**
 * @author manxingfu
 * @date 2023/2/9
 * 用于前端首页的会议室条件查询
 */
public class ConferenceRoomQuery implements Serializable {
    private String roomName;

    private Integer type;

    private Integer pageNo;

    private Integer pageSize;

    private Integer sortType; // {1: 按面积, 2: 按人数, 3: 按评分, 4: 按预约次数}

    public ConferenceRoomQuery(String roomName, Integer type, Integer pageNo, Integer pageSize, Integer sortType) {
        this.roomName = roomName;
        this.type = type;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortType = sortType;
    }

    public ConferenceRoomQuery() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    @Override
    public String toString() {
        return "AppointQuery{" +
                "roomName='" + roomName + '\'' +
                ", type=" + type +
                ", sortType=" + sortType +
                '}';
    }
}
