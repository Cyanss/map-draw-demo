package cn.bluethink.operator;

import java.awt.event.MouseEvent;

import cn.bluethink.dto.GraphDTO;
import cn.bluethink.graph.GraphView;
import cn.bluethink.graph.Line;
import cn.bluethink.util.DataUtil;
import cn.bluethink.util.JsonUtil;
import cn.bluethink.util.RestUtil;

public class MouseDrawLineOp implements IMouseOperator {

	private Line mLine;
	private boolean mLButtonDown = false;
	private boolean isNew = false;
	private boolean isValid = false;
	private GraphView mGraphView;
	
	public MouseDrawLineOp(GraphView view) {
		this.mGraphView = view;
	}
	
	@Override
	public void mouseClick(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if ( !mLButtonDown )
			return;
		if (isValid) {
			mGraphView.mGraphs.add(mLine);
			isNew = true;
			isValid = false;
			GraphDTO graphDTO = DataUtil.graphSplicing(mLine);
			if( graphDTO != null) {
				String jsonBody = JsonUtil.bean2Json(graphDTO);
				RestUtil.graphAdd(jsonBody);
			}
		}
		mLButtonDown = false;
		mGraphView.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		mLButtonDown = true;
		if (mLine == null) {
			mLine = new Line();
		}
		if (isNew) {
			mLine = new Line();
		}
		mLine.getFromPoint().setLocation(event.getPoint());
		mLine.getToPoint().setLocation(event.getPoint());
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if ( !mLButtonDown ) 
			return;
		isValid = true;
		mLine.getToPoint().setLocation(event.getPoint());
		mGraphView.refresh();
		mLine.draw(mGraphView.getGraphics());
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		if ( !mLButtonDown ) 
			return;
		mLine.draw(mGraphView.getGraphics());
		mLine.getToPoint().setLocation(event.getPoint());
		mLine.draw(mGraphView.getGraphics());
	}

}
