package com.mxf.service;

import com.mxf.domain.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author manxingfu
 * @date 2023/1/10
 */
public interface ApiService {
    // 统一上传文件
    Result uploadFile(MultipartFile file);

    // 获取服务器信息
    Result querySystemInfo();
}
