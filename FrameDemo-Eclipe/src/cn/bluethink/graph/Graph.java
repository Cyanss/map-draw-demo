package cn.bluethink.graph;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import cn.bluethink.entity.GraphInfo;

public abstract class Graph{

	public final static int SEL_TOLERANCE = 5;
	
	protected Color mColor;
	
	public Graph() {
		mColor = Color.BLACK;
	}
	public Color getColor() {
		return mColor;
	}
	public void setColor(Color color) {
		this.mColor = color;
	}
	public abstract Color getRandomColor();
	public abstract String type();
	
	public abstract int getHandleCount();
	public abstract Point getHandle(int index);
	public abstract void draw(Graphics c);
	public abstract void drawAsSelect(Graphics c);
	
	public abstract int hitTest(Point pt);
	public abstract void moveTo(int dx, int dy);
	public abstract void moveHandleTo(int handleIndex, Point pt);
	
	public abstract GraphInfo getGraphInfo();
}
