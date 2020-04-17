/**
 * project:InscreenProximityDemo <BR>
 * file name:SingleEndReference.java <BR>
 * @author david.dong
 * create:2015年2月25日下午3:38:13
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import java.util.ArrayList;

/**
 * project:InscreenProximityDemo <BR>
 * class name:SingleEndReference <BR>
 * @author david.dong
 * create:2015年2月25日下午3:38:13
 */
public class SingleEndReference {

	private ArrayList<XSingleEndReference> evenXSEReference = new ArrayList<XSingleEndReference>();
	private ArrayList<XSingleEndReference> oddXSEReference = new ArrayList<XSingleEndReference>();
	private final int type = 248;
	/**
	 * @author david.dong
	 * @return the evenXSEReference
	 */
	public ArrayList<XSingleEndReference> getEvenXSEReference() {
		return evenXSEReference;
	}
	/**
	 * @author david.dong
	 * @param evenXSEReference the evenXSEReference to set
	 */
	public void setEvenXSEReference(ArrayList<XSingleEndReference> evenXSEReference) {
		this.evenXSEReference = evenXSEReference;
	}
	/**
	 * @author david.dong
	 * @return the oddXSEReference
	 */
	public ArrayList<XSingleEndReference> getOddXSEReference() {
		return oddXSEReference;
	}
	/**
	 * @author david.dong
	 * @param oddXSEReference the oddXSEReference to set
	 */
	public void setOddXSEReference(ArrayList<XSingleEndReference> oddXSEReference) {
		this.oddXSEReference = oddXSEReference;
	}
	/**
	 * @author david.dong
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	
}
