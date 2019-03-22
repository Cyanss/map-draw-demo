package cn.bluethink.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import cn.bluethink.config.ParamConfig;
import cn.bluethink.config.RestConfig;
import cn.bluethink.entity.GraphInfo;
import cn.bluethink.entity.ResultInfo;
import cn.bluethink.entity.UserInfo;

public class RestUtil {
	
	public static ParamConfig paramConfig;
	public static RestConfig restConfig;
	public static String jsonBody = null;
	public static String method = "GET";
	public static String path = "";
	public static String type = "";
	public static boolean isSuccess = false;
	public static Map<String, Object> paramList = new HashMap<>();
	
	public RestUtil(String path, String type) {
		RestUtil.path = path;
		RestUtil.type = type;
		
	}
	
	public RestUtil(String path, String type, Map<String, Object> paramList) {
		RestUtil.path = path;
		RestUtil.type = type;
		RestUtil.paramList = paramList;
		initConfig();
	}
	
	public static void initConfig() {
		paramConfig = new ParamConfig(paramList);
		restConfig = new RestConfig(path,type,paramConfig.getParamsUrl());
	}
	
	public static String httpRequest() {
		String resultString = null;
		HttpURLConnection connection = null;
		try {
			URL url=new URL(restConfig.getUrl());  
			connection = (HttpURLConnection) url.openConnection();  
			connection.setRequestMethod(method);  
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
			connection.setRequestProperty("Accept","application/json");
			connection.setConnectTimeout(5000);  
			
			if (jsonBody != null && !"".equals(jsonBody)) {
                byte[] writebytes = jsonBody.getBytes();
                connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = connection.getOutputStream();
                outwritestream.write(jsonBody.getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
			
			if(connection.getResponseCode()==200){    
				resultString = dataAnalysis(connection);
				isSuccess = true;
			} else {
				resultString = connection.getResponseMessage();
				isSuccess = false;
			}
		}catch (MalformedURLException e) {
	        e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}			
		}
		reset();
		return resultString;
	}
	
	public static UserInfo userLogin(){
		path = "login";
		type = "user";
		method = "GET";
		initConfig();
		UserInfo userInfo = null;
		String jsonString = httpRequest();
		if(isSuccess && jsonString != null){
			ResultInfo result = JsonUtil.json2Bean(jsonString, ResultInfo.class);
		    if (result.getCode() == 0) {		    
		    	userInfo = JsonUtil.json2Bean(result.getData(), UserInfo.class);
		    	DataUtil.currentUser = userInfo;
		    	JOptionPane.showMessageDialog(DataUtil.jFrame,userInfo. getUserName() + " 登陆成功","提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(DataUtil.jFrame, result.getMsg(),"提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (!isSuccess && jsonString != null) {
			JOptionPane.showMessageDialog(DataUtil.jFrame, jsonString,"提示", JOptionPane.INFORMATION_MESSAGE);
		}
		return  userInfo;
		
	}
	
	public static GraphInfo graph(){
		GraphInfo graphInfo = null;
		String jsonString = httpRequest();
		if(isSuccess && jsonString != null){    
			ResultInfo result = JsonUtil.json2Bean(jsonString, ResultInfo.class);
		    if (result.getCode() == 0) {		    
		    	graphInfo = JsonUtil.json2Bean(result.getData(), GraphInfo.class);
			} else {
				JOptionPane.showMessageDialog(DataUtil.jFrame, result.getMsg(),"提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (!isSuccess && jsonString != null) {
			JOptionPane.showMessageDialog(DataUtil.jFrame, jsonString,"提示", JOptionPane.INFORMATION_MESSAGE);
		}
		return graphInfo;
	}
	
	public static List<GraphInfo> graphList(){
		List<GraphInfo> graphInfoList = null;
		path = "all";
		type = "graph";
		method = "GET";
		initConfig();
		String jsonString = httpRequest();
		if(isSuccess && jsonString != null){    
			ResultInfo result = JsonUtil.json2Bean(jsonString, ResultInfo.class);
		    if (result.getCode() == 0) {		    
		    	graphInfoList = JsonUtil.json2BeanList(result.getData(), GraphInfo.class);
			} else {
				JOptionPane.showMessageDialog(DataUtil.jFrame, result.getMsg(),"提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (!isSuccess && jsonString != null) {
			JOptionPane.showMessageDialog(DataUtil.jFrame, jsonString,"提示", JOptionPane.INFORMATION_MESSAGE);
		}
		isSuccess = false;
		return graphInfoList;
	}
	
	public static UserInfo userAdd(String jsonBody){
		UserInfo userInfo = null;
		path = "add";
		type = "user";
		method = "POST";
		initConfig();
		RestUtil.jsonBody = jsonBody;
		String jsonString = httpRequest();
		if(isSuccess && jsonString != null){    
			ResultInfo result = JsonUtil.json2Bean(jsonString, ResultInfo.class);
        	if (result.getCode() == 0) {
        		userInfo = JsonUtil.json2Bean(result.getData(), UserInfo.class);
			} else {
				JOptionPane.showMessageDialog(DataUtil.jFrame, result.getMsg(),"提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (!isSuccess && jsonString != null) {
			JOptionPane.showMessageDialog(DataUtil.jFrame, jsonString,"提示", JOptionPane.INFORMATION_MESSAGE);
		}
		isSuccess = false;
		return userInfo;
	}

	public static String graphAdd(String jsonBody){
		String resultString = null;
		path = "add";
		type = "graph";
		method = "POST";
		initConfig();
		RestUtil.jsonBody = jsonBody;
		String jsonString = httpRequest();
		if(isSuccess && jsonString != null){    
			ResultInfo result = JsonUtil.json2Bean(jsonString, ResultInfo.class);
        	if (result.getCode() != 0) {
				JOptionPane.showMessageDialog(DataUtil.jFrame, result.getMsg(),"提示", JOptionPane.INFORMATION_MESSAGE);
			}
        	resultString = result.getMsg();
		} else if (!isSuccess && jsonString != null) {
			resultString = jsonString;
			JOptionPane.showMessageDialog(DataUtil.jFrame, jsonString,"提示", JOptionPane.INFORMATION_MESSAGE);
		}
		isSuccess = false;
		return resultString;
	}
	public static String graphUpdate(String jsonBody){
		String resultString = null;
		path = "update";
		type = "graph";
		method = "PUT";
		initConfig();
		RestUtil.jsonBody = jsonBody;
		String jsonString = httpRequest();
		if(isSuccess && jsonString != null){    
			ResultInfo result = JsonUtil.json2Bean(jsonString, ResultInfo.class);
        	if (result.getCode() != 0) {
				JOptionPane.showMessageDialog(DataUtil.jFrame, result.getMsg(),"提示", JOptionPane.INFORMATION_MESSAGE);
			}
        	resultString = result.getMsg();
		} else if (!isSuccess && jsonString != null) {
			resultString = jsonString;
			JOptionPane.showMessageDialog(DataUtil.jFrame, jsonString,"提示", JOptionPane.INFORMATION_MESSAGE);
		}
		isSuccess = false;
		return resultString;
	}
	public static String dataAnalysis(HttpURLConnection connection) throws IOException {
		InputStream inputStream = connection.getInputStream();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
	    StringBuffer stringBuffer = new StringBuffer();
        String strRead = null;
        while ((strRead = reader.readLine()) != null) {
        	stringBuffer.append(strRead);
        	stringBuffer.append("\r\n");
        }
        reader.close();
        inputStream.close();
		return stringBuffer.toString();
	}

	public static void reset() {
		jsonBody = null;
		method = "GET";
		path = "";
		type = "";
		paramList.clear();
	}
}
