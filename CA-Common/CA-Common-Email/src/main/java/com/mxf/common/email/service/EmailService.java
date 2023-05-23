package com.mxf.common.email.service;

import com.mxf.common.email.domain.Email;

/**
 * @author manxingfu
 * @date 2023/5/22 17:28
 * @desc
 */
public interface EmailService {
    void send(Email mail);
}
