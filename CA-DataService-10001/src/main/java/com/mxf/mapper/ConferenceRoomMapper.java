package com.mxf.mapper;


import com.mxf.domain.ConferenceRoom;
import com.mxf.domain.query.CRQueryCondition;
import com.mxf.domain.query.ConferenceRoomQuery;
import com.mxf.domain.vo.ConferenceRoomVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-13
 */
@Mapper
public interface ConferenceRoomMapper extends BaseMapper<ConferenceRoom> {
    ConferenceRoomVo queryById(@Param("id") int id);

    List<ConferenceRoomVo> queryByCount(@Param("count") Integer count);

    List<ConferenceRoomVo> queryAll();

    List<ConferenceRoomVo> queryByCondition(@Param("queryCondition") CRQueryCondition queryCondition);

    List<ConferenceRoomVo> queryByAppointQuery(@Param("queryCondition") ConferenceRoomQuery queryCondition);
}
