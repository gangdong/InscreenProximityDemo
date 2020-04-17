/**
 * project:InscreenProximityDemo <BR>
 * file name:SelfReference.java <BR>
 * @author david.dong
 * create:2015年2月25日下午4:25:30
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import java.util.ArrayList;

/**
 * project:InscreenProximityDemo <BR>
 * class name:SelfReference <BR>
 * @author david.dong
 * create:2015年2月25日下午4:25:30
 */
public class SelfReference {

	private final int type = 248;
	private ArrayList<XSelfReference> evenXSelfReference = new ArrayList<XSelfReference>();
	private ArrayList<XSelfReference> oddXSelfReference = new ArrayList<XSelfReference>();
	private ArrayList<YSelfReference> ySelfReference = new ArrayList<YSelfReference>();
	/**
	 * @author david.dong
	 * @return the evenXSelfReference
	 */
	public ArrayList<XSelfReference> getEvenXSelfReference() {
		return evenXSelfReference;
	}
	/**
	 * @author david.dong
	 * @param evenXSelfReference the evenXSelfReference to set
	 */
	public void setEvenXSelfReference(ArrayList<XSelfReference> evenXSelfReference) {
		this.evenXSelfReference = evenXSelfReference;
	}
	/**
	 * @author david.dong
	 * @return the oddXSelfReference
	 */
	public ArrayList<XSelfReference> getOddXSelfReference() {
		return oddXSelfReference;
	}
	/**
	 * @author david.dong
	 * @param oddXSelfReference the oddXSelfReference to set
	 */
	public void setOddXSelfReference(ArrayList<XSelfReference> oddXSelfReference) {
		this.oddXSelfReference = oddXSelfReference;
	}
	/**
	 * @author david.dong
	 * @return the ySelfReference
	 */
	public ArrayList<YSelfReference> getySelfReference() {
		return ySelfReference;
	}
	/**
	 * @author david.dong
	 * @param ySelfReference the ySelfReference to set
	 */
	public void setySelfReference(ArrayList<YSelfReference> ySelfReference) {
		this.ySelfReference = ySelfReference;
	}
	/**
	 * @author david.dong
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	
}
