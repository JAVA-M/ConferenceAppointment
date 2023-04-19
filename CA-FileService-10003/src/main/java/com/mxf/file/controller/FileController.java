package com.mxf.file.controller;

import com.mxf.domain.Result;
import com.mxf.file.service.ISysFileService;
import com.mxf.system.api.domain.SysFile;
import com.mxf.utils.file.FileUtils;
import com.mxf.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 *
 * @author manxingfu
 */
@Api("文件请求接口")
@RestController
public class FileController extends BaseController {
    @Autowired
    private ISysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @ApiOperation(value = "文件上传接口")
    @PostMapping("upload")
    public Result upload(@ApiParam(value = "上传的文件", required = true) MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return Result.success(sysFile);
        } catch (Exception e) {
            logger.error("上传文件失败", e);
            return Result.failure(e.getMessage());
        }
    }
}
