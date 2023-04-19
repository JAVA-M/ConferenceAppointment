package com.mxf.domain.vo;

import java.util.List;

/**
 * @author manxingfu
 * @date 2023/2/6
 */
public class Mail {
    // 邮件主题
    private String subject;

    // 邮件内容
    private String content;

    public Mail(String subject, String content, List<String> addresses) {
        this.subject = subject;
        this.content = content;
        this.addresses = addresses;
    }

    public Mail() {
    }

    // 接收者的账号
    private List<String> addresses;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
