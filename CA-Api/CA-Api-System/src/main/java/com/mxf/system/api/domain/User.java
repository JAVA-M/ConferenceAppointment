package com.mxf.system.api.domain;

import java.io.Serializable;

/**
 * @author manxingfu
 * @date 2023/2/23
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private Integer role;

    private String phone;

    private String email;

    private Integer status;


    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStatus() {
        return status;
    }
}
