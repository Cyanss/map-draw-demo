package cn.bluethink.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import cn.bluethink.entity.GraphInfo;
import cn.bluethink.util.ColorUtil;

public class Polygon extends Graph {
	
	private List<Point> pointList = new ArrayList<Point>();
	private int[] xPoints; 
	private int[] yPoints;
	private final Color randomColor;
	private GraphInfo graphInfo;
	
	public Polygon(){
		randomColor = ColorUtil.generateRandomColor();
	}

	public Polygon(List<Point> pointList, Color color, GraphInfo graphInfo) {
		super();
		if (pointList != null && !pointList.isEmpty()) {
			this.pointList = pointList;
		}
		randomColor = color;
		this.graphInfo = graphInfo;
	}
	@Override
	public GraphInfo getGraphInfo() {
		return graphInfo;
	}

	public void setGraphInfo(GraphInfo graphInfo) {
		this.graphInfo = graphInfo;
	}

	public List<Point> getPointList() {
		return pointList;
	}

	public void setPointList(List<Point> pointList) {
		if (pointList != null && !pointList.isEmpty()) {
			this.pointList = pointList;
		}
	}
	
	public int addPoint(Point point) {
		if (point != null) {
			pointList.add(point);
			return pointList.size();
		} else {
			return -1;
		}
	}
	
	public int addPoint(int x, int y) {
		pointList.add(new Point(x, y));
		return pointList.size();
	}
	
	public Point getLast() {
		if (pointList.size() > 0) {
			return pointList.get(pointList.size() - 1);
		} else {
			return new Point(0, 0);
		}
		
	}
	
	public Point getFirst() {
		if (pointList.size() > 0) {
			return pointList.get(0);
		} else {
			return new Point(0, 0);
		}
		
	}
	
	public void overDraw() {
		pointList.remove(pointList.size()-1);
	}

	@Override
	public String type() {
		return "polygon";
	}

	@Override
	public int getHandleCount() {
		return pointList.size();
	}

	@Override
	public Point getHandle(int index) {
		if (pointList.size() > index) {
			return pointList.get(index);
		} else {
			return new Point(0, 0);
		}
	}

	@Override
	public void draw(Graphics c) {
		xPoints = new int[pointList.size()];
		yPoints = new int[pointList.size()];
		for (int i = 0; i < pointList.size(); i++) {
			xPoints[i]=pointList.get(i).x;
			yPoints[i]=pointList.get(i).y;
		}
		Color oldColor= c.getColor();
		c.setColor(randomColor);
		/* 绘制多边形边线 */
		for (int i = 0; i < pointList.size() - 1; i++) {
			c.drawLine((int)pointList.get(i).getX(), (int)pointList.get(i).getY(), (int)pointList.get(i+1).getX(), (int)pointList.get(i+1).getY());
		}
		c.drawLine((int)getLast().getX(), (int)getLast().getY(), (int)getFirst().getX(), (int)getFirst().getY());
		/* 填充多边形 */
		
		c.fillPolygon(xPoints, yPoints,pointList.size());
		c.setColor(oldColor);

	}

	@Override
	public void drawAsSelect(Graphics c) {
		xPoints = new int[pointList.size()];
		yPoints = new int[pointList.size()];
		for (int i = 0; i < pointList.size(); i++) {
			xPoints[i]=pointList.get(i).x;
			yPoints[i]=pointList.get(i).y;
		}
		Color oldColor= c.getColor();
		c.setColor(Color.YELLOW);

		for (int i = 0; i < pointList.size() - 1; i++) {
			c.drawLine((int)pointList.get(i).getX(), (int)pointList.get(i).getY(), (int)pointList.get(i+1).getX(), (int)pointList.get(i+1).getY());
		}
		
		c.drawLine((int)getLast().getX(), (int)getLast().getY(), (int)getFirst().getX(), (int)getFirst().getY());
		c.fillPolygon(xPoints, yPoints,pointList.size());
		c.setColor(oldColor);
	}

	@Override
	public int hitTest(Point pt) {
		for (int i = 0; i < pointList.size() - 1; i++) {
			if( Math.abs(pt.getX()-pointList.get(i).getX()) <= SEL_TOLERANCE && Math.abs(pt.getY()-pointList.get(i).getY()) <= SEL_TOLERANCE ){
				return i;
			} else if ( pt.getX() >= (pointList.get(i).getX() > pointList.get(i+1).getX() ? pointList.get(i+1).getX() : pointList.get(i).getX()) &&
				      pt.getX() <= (pointList.get(i).getX() < pointList.get(i+1).getX() ? pointList.get(i+1).getX() : pointList.get(i).getX()) &&
					  pt.getY() >= (pointList.get(i).getY() > pointList.get(i+1).getY() ? pointList.get(i+1).getY() : pointList.get(i).getY()) &&
				      pt.getY() <= (pointList.get(i).getY() < pointList.get(i+1).getY() ? pointList.get(i+1).getY() : pointList.get(i).getY()) ){
				return -1;
			}				
		}
		if( Math.abs(pt.getX()- getLast().getX()) <= SEL_TOLERANCE && Math.abs(pt.getY()-getLast().getY()) <= SEL_TOLERANCE ){
			return pointList.size()-1;
		} 
		return -2;
	}

	@Override
	public void moveTo(int dx, int dy) {
		pointList.forEach(mPoint -> {
			mPoint.translate(dx, dy);
		});
	}

	@Override
	public void moveHandleTo(int handleIndex, Point pt) {
		if(pt == null) {
			return;
		}
		if (pointList.size() > handleIndex) {
			pointList.get(handleIndex).setLocation(pt.getX(), pt.getY());
		} 
	}
	@Override
	public Color getRandomColor() {
		return randomColor;
	}

}
