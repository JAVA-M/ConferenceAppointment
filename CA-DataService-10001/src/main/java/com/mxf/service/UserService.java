package com.mxf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxf.domain.User;
import com.mxf.domain.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 满兴富
 * @since 2022-12-13
 */
public interface UserService extends IService<User> {

     List<UserVo> queryAllVoUser();

     // 根据用户名获取管理员用户
     User queryAdminUserByName(String username);
     // 获取全部管理员用户
     List<User> queryAllAdminUser();

     // 添加管理员用户
     boolean insertAdminUser(User user);

     // 检查用户名是否重复
     boolean queryNameIsRepeat(String username);

     User queryAdminUserByToken(HttpServletRequest request);

     UserVo queryAdminUserVoByToken(HttpServletRequest request);
}
