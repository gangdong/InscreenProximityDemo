/**
 * project:InscreenProximityDemo <BR>
 * file name:YSelfDelta.java <BR>
 * @author david.dong
 * create:2015年2月25日下午12:10:59
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:YSelfDelta <BR>
 * @author david.dong
 * create:2015年2月25日下午12:10:59
 */
public class YSelfDelta {

	private int index;
	private int delta;
	/**
	 * constructor of class:YSelfDelta <BR>
	 * @author david.dong
	 * create:2015年2月25日下午12:11:24
	 */
	public YSelfDelta() {
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
