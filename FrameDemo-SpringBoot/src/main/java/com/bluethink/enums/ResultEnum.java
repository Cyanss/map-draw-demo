package com.bluethink.enums;

import lombok.Getter;


/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@Getter
public enum  ResultEnum {
    DATA_IS_EMPTY(1,"查询数据为空"),
    PARAM_ERROR(2, "参数不正确"),
    USER_INFO_NOT_EXIT(3, "用户信息不存在"),
    USER_NAME_IS_EXIT(4, "用户名已存在"),
    USER_ID_IS_NULL(5, "用户编号不能为空"),
    GRAPH_INFO_NOT_EXIT(6, "图形信息不存在"),
    GRAPH_NAME_IS_EXIT(7, "图形名已存在"),
    GRAPH_NAME_IS_NULL(8,"图形名不能为空"),
    USER_PHONE_IS_NULL(9,"手机号码不能为空"),
    USER_NAME_IS_NULL(10,"用户名不能为空"),
    USER_DELETE_ILLEGAL_OPERATION(11, "请先删除该用户的上传的图形信息"),
    GRAPH_TYPE_IS_NULL(12, "图形类型不能为空"),
    GRAPH_COLOR_IS_NULL(13, "图形颜色不能为空"),
    GRAPH_DATA_IS_NULL(14, "图形数据不能为空"),
    GRAPH_ID_IS_NULL(15, "图形编号不能为空"),
    DATA_IS_NULL(16, "当前用户没有存储数据"),
    PASSWORD_IS_NULL(17, "密码输入为空"),
    PASSWORD_IS_WRONG(18, "密码输错误"),
    NO_USER_IS_LOGIN(19, "当前没有用户登陆，请登陆后重试"),
    UNKNOWN_USER_AUTH(20,"未知的权限"),
    USER_AUTH_IS_NOT_ENOUGH(21,"用户权限不足")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
