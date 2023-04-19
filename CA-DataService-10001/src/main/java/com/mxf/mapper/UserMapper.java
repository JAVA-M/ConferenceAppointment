package com.mxf.mapper;

import com.mxf.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxf.domain.vo.UserVo;
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
public interface UserMapper extends BaseMapper<User> {
    // 视图层用户信息查询
    List<UserVo> queryAllUser();

    UserVo queryUserVoById(@Param("id") int id);
}
