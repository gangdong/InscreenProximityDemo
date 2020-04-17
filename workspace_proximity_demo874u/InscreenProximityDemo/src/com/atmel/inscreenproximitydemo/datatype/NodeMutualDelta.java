/**
 * project:InscreenProximityDemo <BR>
 * file name:NodeMutualDelta.java <BR>
 * @author david.dong
 * create:2015年2月19日下午10:48:33
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import android.graphics.Point;

/**
 * project:InscreenProximityDemo <BR>
 * class name:NodeMutualDelta <BR>
 * @author david.dong
 * create:2015年2月19日下午10:48:33
 */
public class NodeMutualDelta {

	private Point index = new Point(0,0);
	private int delta = 0;
	
	
	
	
	/**
	 * constructor of class:NodeMutualDelta <BR>
	 * @author david.dong
	 * create:2015年2月19日下午10:50:11
	 */
	public NodeMutualDelta() {
		
	}
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
