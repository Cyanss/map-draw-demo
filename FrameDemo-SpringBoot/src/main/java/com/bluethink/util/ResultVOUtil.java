package com.bluethink.util;

import com.bluethink.vo.ResultVO;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO success(String message, Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg(message);
        return resultVO;
    }
    public static ResultVO success() {
        return success(null);
    }
    public static ResultVO success(String message) {
        return success(message,null);
    }
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}

