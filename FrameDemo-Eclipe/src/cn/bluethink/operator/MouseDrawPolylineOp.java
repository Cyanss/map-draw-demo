package cn.bluethink.operator;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import cn.bluethink.dto.GraphDTO;
import cn.bluethink.graph.GraphView;
import cn.bluethink.graph.Polyline;
import cn.bluethink.util.DataUtil;
import cn.bluethink.util.JsonUtil;
import cn.bluethink.util.RestUtil;

public class MouseDrawPolylineOp implements IMouseOperator{
	private Polyline mPolyline;
	private boolean mLButtonDown = false;
	private boolean isNew = false;
	private GraphView mGraphView;
	
	public MouseDrawPolylineOp(GraphView view) {
		this.mGraphView = view;
	}
	
	@Override
	public void mouseClick(MouseEvent event) {
		// TODO Auto-generated method stub
		if ((event.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
			mLButtonDown = true;
			if (mPolyline == null) {
				mPolyline = new Polyline();
			}
			if (isNew) {
				mPolyline = new Polyline();
				isNew = false;
			}
			if (mPolyline.getHandleCount() != 1) {
				mPolyline.addPoint(event.getPoint());
			}
		}
		if ((event.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			if ( !mLButtonDown )
				return;
			if (mPolyline.getHandleCount() <= 3) {
				mPolyline.getPointList().clear();
			} else {
				isNew = true;
				mGraphView.mGraphs.add(mPolyline);
				mPolyline.overDraw();
				GraphDTO graphDTO = DataUtil.graphSplicing(mPolyline);
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
		if (mPolyline.getHandleCount() == 1) {
			mPolyline.addPoint(event.getPoint());
		}
		mPolyline.draw(mGraphView.getGraphics());
		mPolyline.getLast().setLocation(event.getPoint());
		mGraphView.refresh();
		mPolyline.draw(mGraphView.getGraphics());
	}

}
