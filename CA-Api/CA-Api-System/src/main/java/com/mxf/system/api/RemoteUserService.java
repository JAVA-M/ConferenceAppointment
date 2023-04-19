package com.mxf.system.api;

import com.mxf.domain.Result;
import com.mxf.system.api.domain.User;
import com.mxf.system.api.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteUserService", value = "ca-data-service-10001", fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping("/userinfo/admin/queryByName")
    public Result<User> queryUserInfoByName(@RequestParam("username") String username);

    @PostMapping("/userinfo/admin/insert")
    public Result insertAdminUser(User user);
}
