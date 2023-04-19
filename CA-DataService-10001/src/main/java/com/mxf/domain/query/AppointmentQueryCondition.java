package com.mxf.domain.query;

import java.io.Serializable;

/**
 * @author manxingfu
 * @date 2023/1/10
 */
public class AppointmentQueryCondition implements Serializable {

    // 页号
    private Integer page;
    // 页大小
    private Integer size;
    // 预约状态
    private Integer appointmentStatus;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Integer appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public String toString() {
        return "AppointmentQueryCondition{" +
                "page=" + page +
                ", size=" + size +
                ", appointmentStatus=" + appointmentStatus +
                '}';
    }
}
