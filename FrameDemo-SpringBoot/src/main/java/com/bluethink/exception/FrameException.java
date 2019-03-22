package com.bluethink.exception;

import com.bluethink.enums.ResultEnum;
import lombok.Getter;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@Getter
public class FrameException extends RuntimeException{
    private Integer code;
    public FrameException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public FrameException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
