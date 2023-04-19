package com.mxf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxf.domain.LeaveMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 满兴富
 * @since 2023-03-09
 */
@Mapper
public interface LeaveMessageMapper extends BaseMapper<LeaveMessage> {
    List<LeaveMessage> queryAllLeavingMessage(@Param("current") int current, @Param("pageSize") int pageSize, @Param("timeOrder") boolean timeOrder);
}
