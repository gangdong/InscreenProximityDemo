/**
 * project:InscreenProximityDemo <BR>
 * file name:SelfDelta.java <BR>
 * @author david.dong
 * create:2015年2月25日下午12:12:27
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import java.util.ArrayList;

/**
 * project:InscreenProximityDemo <BR>
 * class name:SelfDelta <BR>
 * @author david.dong
 * create:2015年2月25日下午12:12:27
 */
public class SelfDelta {

	private ArrayList<XSelfDelta> xSelfRawDelta = new ArrayList<XSelfDelta>();
	private ArrayList<YSelfDelta> ySelfRawDelta = new ArrayList<YSelfDelta>();
	
	private final int type = 247;

	/**
	 * constructor of class:SelfDelta <BR>
	 * @author david.dong
	 * create:2015年2月25日下午12:14:49
	 */
	public SelfDelta() {
	}

	/**
	 * @author david.dong
	 * @return the xSelfRawDelta
	 */
	public ArrayList<XSelfDelta> getxSelfRawDelta() {
		return xSelfRawDelta;
	}

	/**
	 * @author david.dong
	 * @param xSelfRawDelta the xSelfRawDelta to set
	 */
	public void setxSelfRawDelta(ArrayList<XSelfDelta> xSelfRawDelta) {
		this.xSelfRawDelta = xSelfRawDelta;
	}

	/**
	 * @author david.dong
	 * @return the ySelfRawDelta
	 */
	public ArrayList<YSelfDelta> getySelfRawDelta() {
		return ySelfRawDelta;
	}

	/**
	 * @author david.dong
	 * @param ySelfRawDelta the ySelfRawDelta to set
	 */
	public void setySelfRawDelta(ArrayList<YSelfDelta> ySelfRawDelta) {
		this.ySelfRawDelta = ySelfRawDelta;
	}

	/**
	 * @author david.dong
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	
}
