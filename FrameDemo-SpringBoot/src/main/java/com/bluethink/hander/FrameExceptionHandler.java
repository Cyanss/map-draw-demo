package com.bluethink.hander;

import com.bluethink.exception.FrameException;
import com.bluethink.util.ResultVOUtil;
import com.bluethink.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@ControllerAdvice
public class FrameExceptionHandler {
    @ExceptionHandler(value = FrameException.class)
    @ResponseBody
    public ResultVO handlerCouponException(FrameException e ) {
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
