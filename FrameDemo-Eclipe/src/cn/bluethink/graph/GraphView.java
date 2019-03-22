package cn.bluethink.graph;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import cn.bluethink.operator.IMouseOperator;
import cn.bluethink.operator.MouseDrawLineOp;
import cn.bluethink.operator.MouseDrawPolygonOp;
import cn.bluethink.operator.MouseDrawPolylineOp;
import cn.bluethink.operator.MouseSelectOp;

public class GraphView extends Canvas {
	private static final long serialVersionUID = 1L;

	public ArrayList<Graph> mGraphs = new ArrayList<Graph>();
	public ArrayList<Graph> mSelectedGraphs = new ArrayList<Graph>();
	public HashMap<String, IMouseOperator> mMoustOperator = new HashMap<String, IMouseOperator>();
	public String mCurrentOp = "";
	private BufferedImage bufferedImage = null;

	private Image mImageBuffer = null;
	
	public GraphView() {
		super();
		addMouseListener(new MouseListener());
		addMouseMotionListener(new MouseMotionListener());
		
		mMoustOperator.put("select",new MouseSelectOp(this));
		mMoustOperator.put("line",new MouseDrawLineOp(this));
		mMoustOperator.put("polyline",new MouseDrawPolylineOp(this));
		mMoustOperator.put("polygon",new MouseDrawPolygonOp(this));
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.clearRect(0, 0, getWidth(), getHeight());
//		g.drawRect(100, 100, 400, 400);
		if(mImageBuffer == null) {
			mImageBuffer = createImage(this.getWidth(), this.getHeight());
		}
		Graphics imgg = mImageBuffer.getGraphics();
		imgg.clearRect(0, 0, mImageBuffer.getWidth(this), mImageBuffer.getHeight(this));
		for(Graph graph : mGraphs) {
			graph.draw(imgg);
		}
		for(Graph graph : mSelectedGraphs) {
			graph.drawAsSelect(imgg);
		}
		imgg.setColor(Color.RED);
		for(Graph graph : mSelectedGraphs) {
			int handleCount = graph.getHandleCount();
			for(int i = 0; i < handleCount; i++) {
				Point hp = graph.getHandle(i);
				imgg.drawRect(hp.x - 3, hp.y-3, 6, 6);
			}
		}
		g.drawImage(mImageBuffer, 0, 0, this);
		
	}
	
	public ArrayList<Graph> getmGraphs() {
		return mGraphs;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void refresh() {
		this.getGraphics().drawImage(mImageBuffer, 0, 0, this);
	}
	
	public void onSizeChanged() {
		Image newBuf = createImage(this.getWidth(), this.getHeight());
		if(mImageBuffer != null) {
			newBuf.getGraphics().drawImage(mImageBuffer, 0, 0, this);
			mImageBuffer = newBuf;
		}
	}
	/**
	 * MouseListener监听接口继承MouseAdapter适配器
	 */
	public class MouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			IMouseOperator op = mMoustOperator.get(mCurrentOp);
			if(op != null) {
				op.mousePressed(e);
			}
			super.mousePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			IMouseOperator op = mMoustOperator.get(mCurrentOp);
			if(op != null) {
				op.mouseReleased(e);
			}
			super.mouseReleased(e);
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {
			IMouseOperator op = mMoustOperator.get(mCurrentOp);
			if(op != null) {
				op.mouseClick(e);
			}
			super.mouseClicked(e);
		}

		public void mouseExited(MouseEvent e) {

		}
	}

	/**
	 * 
	 */
	public class MouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			IMouseOperator op = mMoustOperator.get(mCurrentOp);
			if(op != null) {
				op.mouseDragged(e);
			}
			super.mouseDragged(e);
		}

		public void mouseMoved(MouseEvent e) {
			IMouseOperator op = mMoustOperator.get(mCurrentOp);
			if(op != null) {
				op.mouseMoved(e);
			}
			super.mouseMoved(e);
		}
	}
	
	public void clear() {
		mGraphs.clear();
		mSelectedGraphs.clear();
	}
}
