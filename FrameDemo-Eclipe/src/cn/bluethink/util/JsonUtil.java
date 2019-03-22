package cn.bluethink.util;

import java.util.List;
import com.alibaba.fastjson.JSON;

public class JsonUtil {
	public static <T> T json2Bean(String jsonString ,Class<T> beanClass){
        if("".equals(jsonString)){
            return null;
        }
        return JSON.parseObject(jsonString, beanClass);
    }
	
	public static <T> List<T> json2BeanList(String jsonString ,Class<T> beanClass){
        if("".equals(jsonString)){
            return null;
        }
        return JSON.parseArray(jsonString, beanClass);
    }
	
    public static String bean2Json(Object object){
    	String jsonString = null;
        if(object != null){
        	jsonString = JSON.toJSONString(object);
        }
        return jsonString;
    }
}
