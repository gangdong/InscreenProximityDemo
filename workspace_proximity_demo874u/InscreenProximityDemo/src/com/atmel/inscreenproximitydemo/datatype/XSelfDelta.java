/**
 * project:InscreenProximityDemo <BR>
 * file name:XSelfDelta.java <BR>
 * @author david.dong
 * create:2015年2月25日下午12:08:48
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:XSelfDelta <BR>
 * @author david.dong
 * create:2015年2月25日下午12:08:48
 */
public class XSelfDelta {

	private int index;
	private int delta;
	/**
	 * constructor of class:XSelfDelta <BR>
	 * @author david.dong
	 * create:2015年2月25日下午12:09:53
	 */
	public XSelfDelta() {
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
