package cn.bluethink.dto;

public class GraphDTO {
	/** 用户编号 */
    private Integer userId;
    /** 插件名称 */
    private String graphName;
    /** 插件类型 */
    private String graphType;
    /** 图形颜色 */
    private String graphColor;
    /** 图形内容描述 */
    private String graphContent;
    /** 图形数据 */
    private String graphData;
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getGraphName() {
		return graphName;
	}
	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}
	public String getGraphType() {
		return graphType;
	}
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	public String getGraphColor() {
		return graphColor;
	}
	public void setGraphColor(String graphColor) {
		this.graphColor = graphColor;
	}
	public String getGraphContent() {
		return graphContent;
	}
	public void setGraphContent(String graphContent) {
		this.graphContent = graphContent;
	}
	public String getGraphData() {
		return graphData;
	}
	public void setGraphData(String graphData) {
		this.graphData = graphData;
	}
	@Override
	public String toString() {
		return "GraphDTO [userId=" + userId + ", graphName=" + graphName + ", graphType=" + graphType + ", graphColor="
				+ graphColor + ", graphContent=" + graphContent + ", graphData=" + graphData + "]";
	}
    
}
