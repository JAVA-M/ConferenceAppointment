package com.mxf.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author manxingfu
 * @date 2023/3/16
 */
public interface ISysFileService {

    /**
     * 文件上传接口
     * @param file 上传的文件
     * @return 文件访问地址
     */
    String uploadFile(MultipartFile file) throws Exception;
}
