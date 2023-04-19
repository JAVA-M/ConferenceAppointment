package com.mxf.auth.service;

import com.mxf.domain.Result;

/**
 * @author manxingfu
 * @date 2023/3/17
 */
public interface ValidateCodeService {
    Result createValidateCode(String webId);

    void checkValidateCode(String webId, String code);
}
