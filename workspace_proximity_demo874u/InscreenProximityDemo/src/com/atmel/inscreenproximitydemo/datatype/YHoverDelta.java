/**
 * project:InscreenProximityDemo <BR>
 * file name:YHoverDelta.java <BR>
 * @author david.dong
 * create:2015年3月31日下午6:19:56
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:YHoverDelta <BR>
 * @author david.dong
 * create:2015年3月31日下午6:19:56
 */
public class YHoverDelta {

	
	private int index;
	private int delta;
	/**
	 * constructor of class:YHoverDelta <BR>
	 * @author david.dong
	 * create:2015年3月31日下午6:20:23
	 */
	public YHoverDelta() {
		
		index = 0;
		delta = 0;
	}
	/**
	 * @author david.dong
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @author david.dong
	 * @param index the index to set
	 */
	public void setIndex(int index) {
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
