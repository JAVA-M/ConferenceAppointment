package com.mxf.service.impl;


import com.mxf.domain.Result;
import com.mxf.domain.server.Server;
import com.mxf.service.ApiService;
import com.mxf.utils.GeneratorName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author manxingfu
 * @date 2023/1/10
 */
@Service
public class ApiServiceImpl implements ApiService {

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Value("${server.port}")
    private String port;

    private static GeneratorName generatorName = GeneratorName.newInstance();
    @Override
    public Result uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.failure("文件不能为空，请选择要上传的文件");
        }
        String suffix = file.getOriginalFilename().split("\\.")[1];
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        // 获取jar包目录
        String realPath = applicationHome.getSource().getParentFile().toString() + "\\upload\\";
        File storeFile = new File(realPath);
        if (!storeFile.exists()) {
            storeFile.mkdirs();
        }
        String fileName = generatorName.generator(suffix);
        logger.info("上传文件的存储名称: " + fileName);
        try {
            file.transferTo(new File(realPath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 文件路径回传
        return Result.success("http://localhost:" + port + "/upload/" + fileName);
    }

    @Override
    public Result querySystemInfo() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(server);
    }
}
