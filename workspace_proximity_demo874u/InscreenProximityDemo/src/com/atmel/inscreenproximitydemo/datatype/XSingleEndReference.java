/**
 * project:InscreenProximityDemo <BR>
 * file name:XSingleEndReference.java <BR>
 * @author david.dong
 * create:2015年2月25日下午3:35:41
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:XSingleEndReference <BR>
 * @author david.dong
 * create:2015年2月25日下午3:35:41
 */
public class XSingleEndReference {

	private int index;
	private int delta;
	/**
	 * constructor of class:XSingleEndReference <BR>
	 * @author david.dong
	 * create:2015年2月25日下午3:37:18
	 */
	public XSingleEndReference() {
		this.index = 0;
		this.delta = 0;
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
