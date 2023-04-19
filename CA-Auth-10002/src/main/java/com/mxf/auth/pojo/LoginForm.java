package com.mxf.auth.pojo;

import java.io.Serializable;

/**
 * @author manxingfu
 * @date 2023/3/17
 */

public class LoginForm implements Serializable {
    private String username;

    private String password;

    private String captcha;

    private String webId;

    public LoginForm() {
    }

    public LoginForm(String username, String password, String captcha, String webId) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
        this.webId = webId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }
}
