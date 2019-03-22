package com.bluethink.entity;

import lombok.Data;

import java.util.Date;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@Data
public class UserInfo {
    /** 用户编号 */
    private Integer userId;
    /** 用户名字 */
    private String userName;
    /** 用户性别 */
    private Integer userSex;
    /** 用户年龄 */
    private Integer userAge;
    /** 用户手机号 */
    private String userPhone;
    /** 用户邮箱 */
    private String userEmail;
    /** 用户密码 */
    private String userPassword;
    /** 用户权限 */
    private String userAuth;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
