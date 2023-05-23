package com.mxf.common.email.domain;

import java.util.List;

/**
 * @author manxingfu
 * @date 2023/5/22 17:25
 * @desc
 */
public class Email {

    // 邮件主题
    private String subject;

    // 邮件内容
    private String content;

    // 发送人群的ID
    private Integer roleId;

    // 接收者的账号
    private List<String> addresses;

    public String getSubject() {
        return subject;
    }

    public Email setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Email setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Email setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public Email setAddresses(List<String> addresses) {
        this.addresses = addresses;
        return this;
    }
}
