package com.power.modules.sys.form;

import lombok.Data;

@Data
public class PasswordForm {
    /**
     * 原始密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

}
