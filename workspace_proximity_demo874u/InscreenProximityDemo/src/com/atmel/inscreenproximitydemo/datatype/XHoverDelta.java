/**
 * project:InscreenProximityDemo <BR>
 * file name:XHoverDelta.java <BR>
 * @author david.dong
 * create:2015年3月31日下午6:18:26
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:XHoverDelta <BR>
 * @author david.dong
 * create:2015年3月31日下午6:18:26
 */
public class XHoverDelta {

	private int index;
	private int delta;
	/**
	 * constructor of class:XHoverDelta <BR>
	 * @author david.dong
	 * create:2015年3月31日下午6:19:10
	 */
	public XHoverDelta() {
		
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
