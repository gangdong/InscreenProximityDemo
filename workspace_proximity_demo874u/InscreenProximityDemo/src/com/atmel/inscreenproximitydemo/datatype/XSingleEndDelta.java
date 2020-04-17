/**
 * project:InscreenProximityDemo <BR>
 * file name:XSingleEndDelta.java <BR>
 * @author david.dong
 * create:2015年2月24日上午9:45:56
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:XSingleEndDelta <BR>
 * @author david.dong
 * create:2015年2月24日上午9:45:56
 */
public class XSingleEndDelta {

	private int index;
	private int delta;
	/**
	 * constructor of class:XSingleEndDelta <BR>
	 * @author david.dong
	 * create:2015年2月24日上午9:47:56
	 */
	public XSingleEndDelta() {
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
