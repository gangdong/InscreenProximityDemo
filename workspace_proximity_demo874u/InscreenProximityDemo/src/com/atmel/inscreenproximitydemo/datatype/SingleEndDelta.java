/**
 * project:InscreenProximityDemo <BR>
 * file name:SingleEndDelta.java <BR>
 * @author david.dong
 * create:2015年2月24日上午9:49:58
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import java.util.ArrayList;

/**
 * project:InscreenProximityDemo <BR>
 * class name:SingleEndDelta <BR>
 * @author david.dong
 * create:2015年2月24日上午9:49:58
 */
public class SingleEndDelta {

	private final int type = 247;
	private ArrayList<XSingleEndDelta> rawXDelta = new ArrayList<XSingleEndDelta>();
	private ArrayList<YSingleEndDelta> rawYDelta = new ArrayList<YSingleEndDelta>();
	
	
	/**
	 * constructor of class:SingleEndDelta <BR>
	 * @author david.dong
	 * create:2015年2月24日上午9:52:29
	 */
	public SingleEndDelta() {
	}
	/**
	 * @author david.dong
	 * @return the rawDelta
	 */
	public ArrayList<XSingleEndDelta> getRawXDelta() {
		return rawXDelta;
	}
	/**
	 * @author david.dong
	 * @param rawDelta the rawDelta to set
	 */
	public void setRawXDelta(ArrayList<XSingleEndDelta> rawDelta) {
		this.rawXDelta = rawDelta;
	}
	/**
	 * @author david.dong
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @author david.dong
	 * @return the rawYDelta
	 */
	public ArrayList<YSingleEndDelta> getRawYDelta() {
		return rawYDelta;
	}
	/**
	 * @author david.dong
	 * @param rawYDelta the rawYDelta to set
	 */
	public void setRawYDelta(ArrayList<YSingleEndDelta> rawYDelta) {
		this.rawYDelta = rawYDelta;
	}
	
}
