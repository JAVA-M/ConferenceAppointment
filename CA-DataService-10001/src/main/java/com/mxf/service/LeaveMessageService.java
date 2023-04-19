package com.mxf.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mxf.domain.LeaveMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 满兴富
 * @since 2023-03-09
 */
public interface LeaveMessageService extends IService<LeaveMessage> {
    boolean insertLeavingMessage(LeaveMessage leaveMessage);

    List<LeaveMessage> queryAllLeavingMessage(int current, int pageSize, boolean timeOrder);
}
