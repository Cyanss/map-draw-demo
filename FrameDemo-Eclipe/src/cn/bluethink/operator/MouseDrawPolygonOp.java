package cn.bluethink.operator;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import cn.bluethink.dto.GraphDTO;
import cn.bluethink.graph.GraphView;
import cn.bluethink.graph.Polygon;
import cn.bluethink.util.DataUtil;
import cn.bluethink.util.JsonUtil;
import cn.bluethink.util.RestUtil;

public class MouseDrawPolygonOp implements IMouseOperator{
	private Polygon mPolygon;
	private boolean mLButtonDown = false;
	private boolean isNew = false;
	private GraphView mGraphView;
	
	public MouseDrawPolygonOp(GraphView view) {
		this.mGraphView = view;
	}
	
	@Override
	public void mouseClick(MouseEvent event) {
		// TODO Auto-generated method stub
		if ((event.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
			mLButtonDown = true;
			if (mPolygon == null) {
				mPolygon = new Polygon();
			}
			if (isNew) {
				mPolygon = new Polygon();
				isNew = false;
			}
			if (mPolygon.getHandleCount() != 1) {
				mPolygon.addPoint(event.getPoint());
			}
		}
		if ((event.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			if ( !mLButtonDown )
				return;
			
			if (mPolygon.getHandleCount() <= 3) {
				mPolygon.getPointList().clear();
			} else {
				isNew = true;
				mGraphView.mGraphs.add(mPolygon);
				mPolygon.overDraw();
				GraphDTO graphDTO = DataUtil.graphSplicing(mPolygon);
				if( graphDTO != null) {
					String jsonBody = JsonUtil.bean2Json(graphDTO);
					RestUtil.graphAdd(jsonBody);
				}
				
			}
			mLButtonDown = false;
			mGraphView.repaint();
			
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		// TODO Auto-generated method stub
		if ( !mLButtonDown ) 
			return;
		if (mPolygon.getHandleCount() == 1) {
			mPolygon.addPoint(event.getPoint());
		}
		mPolygon.draw(mGraphView.getGraphics());
		mPolygon.getLast().setLocation(event.getPoint());
		mGraphView.refresh();
		mPolygon.draw(mGraphView.getGraphics());
	}

}
