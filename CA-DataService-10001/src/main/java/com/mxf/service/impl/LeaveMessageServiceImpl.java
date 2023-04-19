package com.mxf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxf.domain.LeaveMessage;
import com.mxf.domain.query.SysPage;
import com.mxf.mapper.LeaveMessageMapper;
import com.mxf.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 满兴富
 * @since 2023-03-09
 */
@Service
public class LeaveMessageServiceImpl extends ServiceImpl<LeaveMessageMapper, LeaveMessage> implements LeaveMessageService {

    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    @Override
    public boolean insertLeavingMessage(LeaveMessage leaveMessage) {
        return leaveMessageMapper.insert(leaveMessage) > 0;
    }

    @Override
    public List<LeaveMessage> queryAllLeavingMessage(int current, int pageSize, boolean timeOrder) {
        /*QueryWrapper<LeaveMessage> queryWrapper = new QueryWrapper<>();

        if (timeOrder) {
            queryWrapper.orderByDesc("create_time");
        }
        SysPage<LeaveMessage> sysPage = new SysPage<>();
        sysPage.setPages(current);
        sysPage.setSize(pageSize);

        return leaveMessageMapper.selectPage(sysPage, queryWrapper).getRecords();
        return leaveMessageMapper.selectList(queryWrapper);*/
        return leaveMessageMapper.queryAllLeavingMessage((current - 1) * pageSize, pageSize, timeOrder);
    }
}
