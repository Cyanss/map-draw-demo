package cn.bluethink.operator;

import java.awt.event.MouseEvent;

public interface IMouseOperator {
	/**
	   @roseuid 3F7397BB0261
	   */
	   public void mouseClick(MouseEvent event);

	   /**
	   @roseuid 3F739856035B
	   */
	   public void mouseReleased(MouseEvent event);

	   /**
	   @roseuid 3F7398580119
	   */
	   public void mouseEntered(MouseEvent event);

	   /**
	   @roseuid 3F7398590128
	   */
	   public void mousePressed(MouseEvent event);

	   /**
	   @roseuid 3F73985903B9
	   */
	   public void mouseExited(MouseEvent event);

	   /**
	   @roseuid 3F73985A0280
	   */
	   public void mouseDragged(MouseEvent event);

	   /**
	   @roseuid 3F7398980148
	   */
	   public void mouseMoved(MouseEvent event);

}
