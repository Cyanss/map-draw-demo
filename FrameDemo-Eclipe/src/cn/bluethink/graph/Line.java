package cn.bluethink.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import cn.bluethink.entity.GraphInfo;
import cn.bluethink.util.ColorUtil;

public class Line extends Graph {

	private Point mFromPoint = new Point(0,0);
	private Point mToPoint = new Point(0,0);
	private final Color randomColor;
	private GraphInfo graphInfo;

	public Line(){
		randomColor = ColorUtil.generateRandomColor();
	}
	
	public Line(List<Point> pointList, Color color, GraphInfo graphInfo){
		if (pointList != null && !pointList.isEmpty() && pointList.size() == 2) {
			mFromPoint.setLocation(pointList.get(0).getX(), pointList.get(0).getY());
			mToPoint.setLocation(pointList.get(1).getX(), pointList.get(1).getY());
		}
		randomColor = color;
		this.graphInfo = graphInfo;
	}
	
	public Line(Point from, Point to){
		if(from != null) {
			mFromPoint.setLocation(from.getX(), from.getY());
		}
		if(to != null) {
			mToPoint.setLocation(to.getX(), to.getY());
		}
		randomColor = ColorUtil.generateRandomColor();
	}
	@Override
	public GraphInfo getGraphInfo() {
		return graphInfo;
	}

	public void setGraphInfo(GraphInfo graphInfo) {
		this.graphInfo = graphInfo;
	}

	public Point getFromPoint() {
		return mFromPoint;
	}
	
	public Point getToPoint() {
		return mToPoint;
	}
	
	@Override
	public Color getRandomColor() {
		return randomColor;
	}

	@Override
	public String type() {
		return "line";
	}

	@Override
	public int getHandleCount() {
		return 2;
	}

	@Override
	public Point getHandle(int index) {
		if( index == 0 ) return mFromPoint;
		else if ( index == 1 ) return mToPoint;
		else return new Point(0,0);
	}

	@Override
	public void draw(Graphics c) {
		Color oldColor= c.getColor();
		c.setColor(randomColor);
		
		c.drawLine((int)mFromPoint.getX(), (int)mFromPoint.getY(), (int)mToPoint.getX(), (int)mToPoint.getY());
		c.setColor(oldColor);
	}

	@Override
	public int hitTest(Point pt) {
		
		if( Math.abs(pt.getX()-mFromPoint.getX()) <= SEL_TOLERANCE && Math.abs(pt.getY()-mFromPoint.getY()) <= SEL_TOLERANCE )
			return 0;
		else if ( Math.abs(pt.getX()-mToPoint.getX()) <= SEL_TOLERANCE && Math.abs(pt.getY()-mToPoint.getY()) <= SEL_TOLERANCE )
			return 1;
		else if ( pt.getX() >= (mFromPoint.getX() > mToPoint.getX() ? mToPoint.getX() : mFromPoint.getX()) &&
			      pt.getX() <= (mFromPoint.getX() < mToPoint.getX() ? mToPoint.getX() : mFromPoint.getX()) &&
				  pt.getY() >= (mFromPoint.getY() > mToPoint.getY() ? mToPoint.getY() : mFromPoint.getY()) &&
			      pt.getY() <= (mFromPoint.getY() < mToPoint.getY() ? mToPoint.getY() : mFromPoint.getY()) )//点到直线距离算法
			return -1;
		return -2;
	}

	@Override
	public void moveTo(int dx, int dy) {
		mFromPoint.translate(dx,dy);
		mToPoint.translate(dx,dy);
	}

	@Override
	public void moveHandleTo(int handleIndex, Point pt) {
		if(pt == null) {
			return;
		}
		if ( handleIndex == 0 )
			mFromPoint.setLocation(pt.getX(), pt.getY());
		else if ( handleIndex == 1 )
			mToPoint.setLocation(pt.getX(), pt.getY());
	}

	@Override
	public void drawAsSelect(Graphics c) {
		Color oldColor= c.getColor();
		c.setColor(Color.YELLOW);

		c.drawLine((int)mFromPoint.getX(), (int)mFromPoint.getY(), (int)mToPoint.getX(), (int)mToPoint.getY());
		c.setColor(oldColor);
	}

}
