package com.bluethink.vo;

import lombok.Data;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@Data
public class ResultVO<T> {
    /** 错误码 */
    private Integer code;
    /** 信息 */
    private String msg;
    /** 内容 */
    private T data;
}
