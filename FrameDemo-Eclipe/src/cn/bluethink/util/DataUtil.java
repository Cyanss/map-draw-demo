package cn.bluethink.util;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cn.bluethink.dto.GraphDTO;
import cn.bluethink.entity.GraphInfo;
import cn.bluethink.entity.UserInfo;
import cn.bluethink.graph.Graph;
import cn.bluethink.graph.GraphView;
import cn.bluethink.graph.Line;
import cn.bluethink.graph.Polygon;
import cn.bluethink.graph.Polyline;
import cn.bluethink.jframe.LoginJframe;

public class DataUtil {
	public static UserInfo currentUser = null;
	public static JFrame jFrame = null;
	public static List<GraphDTO> graphDTOList;
	public static List<Graph> graphList;
	public static List<GraphDTO> graphDTOList(GraphView canvas) {
		graphDTOList = new ArrayList<GraphDTO>();
		canvas.getmGraphs().forEach(graph -> {
			graphDTOList.add(graphSplicing(graph));
		});
		return graphDTOList;
	}
	
	public static GraphDTO graphSplicing(Graph graph) {
		GraphDTO graphDTO = graphDataSplicing(graph);
		if (currentUser != null) {
			graphDTO.setUserId(currentUser.getUserId());
		} else {
			JOptionPane.showMessageDialog(jFrame, "未登录，请登录后操作！","提示", JOptionPane.INFORMATION_MESSAGE);
			LoginJframe login = new LoginJframe();
			jFrame = login.getjFrame();
			return null;
		}
		
		String colorString = ColorUtil.encode(graph.getRandomColor());
		graphDTO.setGraphColor(colorString);
		graphDTO.setGraphType(graph.type());
		StringBuffer graphName = new StringBuffer();
		graphName.append(graph.type()).append(colorString);
		graphDTO.setGraphName(graphName.toString());
		graphDTO.setGraphContent(graph.type());
		
		return graphDTO;
	}
	
	public static GraphDTO graphDataSplicing(Graph graph) {
		GraphDTO graphDTO = new GraphDTO();
		if ("line".equals(graph.type())) {
			Line line = (Line) graph;
			graphDTO.setGraphData(lineDataSplicing(line));
		} 
		if ("polyline".equals(graph.type())) {
			Polyline polyline = (Polyline) graph;
			graphDTO.setGraphData(polylineDataSplicing(polyline));
		} 
		if ("polygon".equals(graph.type())) {
			Polygon polygon = (Polygon) graph;
			graphDTO.setGraphData(polygonDataSplicing(polygon));
		} 
		return graphDTO;
	}
	
	public static String lineDataSplicing(Line line) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(line.getFromPoint().x).append(",").append(line.getFromPoint().y)
					.append("/")
					.append(line.getToPoint().x).append(",").append(line.getToPoint().y);
		return stringBuffer.toString();
	}
	
	public static String polylineDataSplicing(Polyline polyline) {
		StringBuffer stringBuffer = new StringBuffer();
		polyline.getPointList().forEach(point -> {
			stringBuffer.append("/")
			.append(point.x).append(",").append(point.y);
		});
		stringBuffer.deleteCharAt(0);
		return stringBuffer.toString();
	}
	
	public static String polygonDataSplicing(Polygon polygon) {
		StringBuffer stringBuffer = new StringBuffer();
		polygon.getPointList().forEach(point -> {
			stringBuffer.append("/")
			.append(point.x).append(",").append(point.y);
		});
		stringBuffer.deleteCharAt(0);
		return stringBuffer.toString();
	}
	
	public static List<Graph> graphList(List<GraphInfo> graphInfoList, GraphView canvas) {
		graphList = new ArrayList<>(canvas.mGraphs);
		graphInfoList.forEach(graphInfo -> {
			Graph graph = graphAnalysis(graphInfo);
			canvas.mGraphs.add(graph);	
		});
		return graphList;
	}
	/**
	 * 读取的数据在添加进GraphView的mGraphs之前
	 * 需要将 mGraphs的数据转存
	 * @param graph
	 * @param canvas
	 * @return
	 */
	public static List<Graph> graphList(Graph graph, GraphView canvas) {
		graphList = new ArrayList<>(canvas.mGraphs);
		if (graph == null) {
			canvas.mGraphs.add(graph);	
		}
		return graphList;
	}
	
	public static Graph graphAnalysis(GraphInfo graphInfo) {
		Graph graph = null;
		List<Point> pointList = dataAnalysis(graphInfo.getGraphData());	
		Color color = ColorUtil.decode(graphInfo.getGraphColor());
		if ("line".equals(graphInfo.getGraphType())) {
			graph = new Line(pointList, color, graphInfo);
		} 
		if ("polyline".equals(graphInfo.getGraphType())) {
			graph = new Polyline(pointList, color, graphInfo);
		}
		if ("polygon".equals(graphInfo.getGraphType())) {
			graph = new Polygon(pointList, color, graphInfo);
		} 
		return graph;
	};
	
	public static List<Point> dataAnalysis(String data) {
		List<Point> pointList = new ArrayList<>();
		
		String[] points = data.split("/");
		List<String> stringList = new ArrayList<>(points.length);
		Collections.addAll(stringList, points);
		stringList.forEach(stringPoint -> {
			String[] dims=  stringPoint.split(",");
			Point point = new Point();
			point.setLocation(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]));
			pointList.add(point);
		});
		return pointList;	
	}

}
