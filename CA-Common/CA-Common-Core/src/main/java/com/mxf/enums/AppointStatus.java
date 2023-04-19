package com.mxf.enums;

/**
 * @author manxingfu
 * @date 2023/2/7
 */
public enum AppointStatus {

    WaitCheckInfo(0, "用户提交预约信息，待管理员审核"),
    Success(1, "预约成功"),
    Failure(2, "审核不通过，预约失败"),
    InProcess(3, "预约的会议进行中"),
    Finish(4, "预约完成"),
    Cancel(5, "用户取消预约"),
    WaitEvaluate(6, "预约待评价");
    private final int statusCode;

    private final String statusMsg;

    AppointStatus(int statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }


}
