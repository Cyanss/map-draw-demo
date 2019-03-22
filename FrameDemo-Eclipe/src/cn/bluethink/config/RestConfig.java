package cn.bluethink.config;

public class RestConfig {
	
	public final String host = "http://localhost:8084/frame";
	public final String graphPath = "/graph/";
	public final String userPath = "/user/";
	
	private String path;
	private String url = "";
	private String type = "";
	private String params = "";

	public RestConfig(String path, String type, String params) {
		this.path = path;
		this.type = type;
		this.params = params;
		urlSplicing();
	}
	
	public RestConfig(String path, String type) {
		this.path = path;
		this.type = type;
		urlSplicing();
	}
	
	public String urlSplicing() {
		StringBuffer stringBuffer = new StringBuffer();
		if ("user".equals(type)) {
			url = stringBuffer.append(host).append(userPath).append(path).append(params).toString();
		}
		if ("graph".equals(type)) {
			url = stringBuffer.append(host).append(graphPath).append(path).append(params).toString();
		}
		return url;
	}


	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
}
