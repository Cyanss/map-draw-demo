package cn.bluethink.entity;

public class ResultInfo {
	private Integer code;
	private String msg;
	private String data;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResultInfo [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
