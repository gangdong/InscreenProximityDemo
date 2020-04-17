/**
 * project:InscreenProximityDemo <BR>
 * file name:HoverDelta.java <BR>
 * @author david.dong
 * create:2015年3月31日下午6:17:56
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import java.util.ArrayList;

/**
 * project:InscreenProximityDemo <BR>
 * class name:HoverDelta <BR>
 * @author david.dong
 * create:2015年3月31日下午6:17:56
 */
public class HoverDelta {

	
	private ArrayList<XHoverDelta> xHoverRawDelta = new ArrayList<XHoverDelta>();
	private ArrayList<YHoverDelta> yHoverRawDelta = new ArrayList<YHoverDelta>();
	
	private final int type = 247;

	/**
	 * @author david.dong
	 * @return the xHoverRawDelta
	 */
	public ArrayList<XHoverDelta> getxHoverRawDelta() {
		return xHoverRawDelta;
	}

	/**
	 * @author david.dong
	 * @param xHoverRawDelta the xHoverRawDelta to set
	 */
	public void setxHoverRawDelta(ArrayList<XHoverDelta> xHoverRawDelta) {
		this.xHoverRawDelta = xHoverRawDelta;
	}

	/**
	 * @author david.dong
	 * @return the yHoverRawDelta
	 */
	public ArrayList<YHoverDelta> getyHoverRawDelta() {
		return yHoverRawDelta;
	}

	/**
	 * @author david.dong
	 * @param yHoverRawDelta the yHoverRawDelta to set
	 */
	public void setyHoverRawDelta(ArrayList<YHoverDelta> yHoverRawDelta) {
		this.yHoverRawDelta = yHoverRawDelta;
	}

	/**
	 * @author david.dong
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
}
