/**
 * project:InscreenProximityDemo <BR>
 * file name:NodeMutualReference.java <BR>
 * @author david.dong
 * create:2015年2月25日下午1:28:48
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import android.graphics.Point;

/**
 * project:InscreenProximityDemo <BR>
 * class name:NodeMutualReference <BR>
 * @author david.dong
 * create:2015年2月25日下午1:28:48
 */
public class NodeMutualReference {
	
	private Point index = new Point(0,0);
	private int delta = 0;
	/**
	 * @author david.dong
	 * @return the index
	 */
	public Point getIndex() {
		return index;
	}
	/**
	 * @author david.dong
	 * @param index the index to set
	 */
	public void setIndex(Point index) {
		this.index = index;
	}
	/**
	 * @author david.dong
	 * @return the delta
	 */
	public int getDelta() {
		return delta;
	}
	/**
	 * @author david.dong
	 * @param delta the delta to set
	 */
	public void setDelta(int delta) {
		this.delta = delta;
	}
	
	
}
