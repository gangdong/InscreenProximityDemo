/**
 * project:InscreenProximityDemo <BR>
 * file name:YSingleEndDelta.java <BR>
 * @author david.dong
 * create:2015年3月30日下午1:01:48
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:YSingleEndDelta <BR>
 * @author david.dong
 * create:2015年3月30日下午1:01:48
 */
public class YSingleEndDelta {

	private int index;
	private int delta;

	/**
	 * constructor of class:YSingleEndDelta <BR>
	 * @author david.dong
	 * create:2015年3月30日下午1:02:44
	 */
	public YSingleEndDelta() {
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
