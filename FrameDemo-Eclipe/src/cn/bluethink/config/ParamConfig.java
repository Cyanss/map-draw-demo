package cn.bluethink.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParamConfig {
	private Map<String, Object> paramList;
	private String paramsUrl;
	
	public ParamConfig(Map<String, Object> paramList) {

		this.paramList = paramList;
		paramSplicing();
	}
	
	public String paramSplicing() {
		 paramsUrl = "";
		if (paramList !=null && !paramList.isEmpty()) {
			StringBuffer stringBuffer = new StringBuffer();
			StringBuffer paramBuffer = new StringBuffer();
			stringBuffer.append("?");
			paramList.forEach((key, value)-> {
				try {
					paramBuffer.append("&").append(key).append("=").append(URLEncoder.encode(String.valueOf(value),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			paramBuffer.deleteCharAt(0);
			stringBuffer.append(paramBuffer);
			paramsUrl = stringBuffer.toString();
		}
		return paramsUrl;
	}
	
	public Map<String, Object> getParamList() {
		return paramList;
	}
	public void setParamList(Map<String, Object> paramList) {
		this.paramList = paramList;
	}
	public String getParamsUrl() {
		return paramsUrl;
	}
	public void setParamsUrl(String paramsUrl) {
		this.paramsUrl = paramsUrl;
	}
	
	
	
}
