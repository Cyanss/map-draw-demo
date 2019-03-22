package cn.bluethink.operator;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import cn.bluethink.dto.GraphDTO;
import cn.bluethink.graph.Graph;
import cn.bluethink.graph.GraphView;
import cn.bluethink.util.DataUtil;
import cn.bluethink.util.JsonUtil;
import cn.bluethink.util.RestUtil;

public class MouseSelectOp implements IMouseOperator {

	public final static int NONE = 0;
	public final static int MOVE = 1;
	public final static int NODE = 2;
	
	protected int mSelectMode = NONE;
	private int mHandleIndex ;
	private Point mPrePoint;
	private GraphView mGraphView;
	private Graph graph = null;
	
	public MouseSelectOp(GraphView view) {
		this.mGraphView = view;
	}
	@Override
	public void mouseClick(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		mSelectMode = NONE;
		mGraphView.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		mSelectMode = NONE;
		mHandleIndex = 0;
		mPrePoint = event.getPoint();
		mGraphView.mSelectedGraphs.clear();
		if(mGraphView == null) {
			return;
		}
		for(Graph g : mGraphView.mGraphs) {
			int handleIndex = g.hitTest(event.getPoint());
			if(handleIndex != -2) {
				mGraphView.mSelectedGraphs.add(g);
				if (!g.equals(graph)) {
					if (graph != null && graph.getGraphInfo() != null) {					
						RestUtil.paramList.put("graphid", graph.getGraphInfo().getGraphId());
						GraphDTO graphDTO = DataUtil.graphSplicing(graph);						
						String jsonBody = JsonUtil.bean2Json(graphDTO);
						RestUtil.graphUpdate(jsonBody);
					}								
					graph = g;
				}
				mHandleIndex = handleIndex;
			}
		}

		int sel_count = mGraphView.mSelectedGraphs.size();
		if ( sel_count == 0) {
			if (graph != null && graph.getGraphInfo() != null) {				
				RestUtil.paramList.put("graphid", graph.getGraphInfo().getGraphId());
				GraphDTO graphDTO = DataUtil.graphSplicing(graph);						
				String jsonBody = JsonUtil.bean2Json(graphDTO);
				RestUtil.graphUpdate(jsonBody);			
				graph = null;
			}
			return;
		}	
			
		if ( sel_count == 1 && mHandleIndex >= 0 ){
			mSelectMode = NODE;
		} else {
			mSelectMode = MOVE;
		}

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if ( mSelectMode == NONE )
			return;
		Graphics g = mGraphView.getGraphics();
		if (mSelectMode == NODE) {
			Graph graph = mGraphView.mSelectedGraphs.get(0);
			graph.draw(g);
			mGraphView.refresh();
			graph.moveHandleTo(mHandleIndex, new Point(event.getX(), event.getY()));
			graph.draw(g);

		} else {
			for (Graph graph : mGraphView.mSelectedGraphs) {
				graph.draw(g);
			}
			mGraphView.refresh();
			for (Graph graph : mGraphView.mSelectedGraphs) {
				graph.moveTo(event.getX() - mPrePoint.x, event.getY() - mPrePoint.y);
				graph.draw(g);
			}
			mPrePoint.setLocation(event.getX(), event.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent event) {
	}

}
