/**
 * project:InscreenProximityDemo <BR>
 * file name:MutualReference.java <BR>
 * @author david.dong
 * create:2015年2月25日下午1:35:36
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import java.util.ArrayList;

/**
 * project:InscreenProximityDemo <BR>
 * class name:MutualReference <BR>
 * @author david.dong
 * create:2015年2月25日下午1:35:36
 */
public class MutualReference {

	private ArrayList<NodeMutualReference> mutualReference = new ArrayList<NodeMutualReference>();
	private final int type = 17;
	/**
	 * @author david.dong
	 * @return the mutualReference
	 */
	public ArrayList<NodeMutualReference> getMutualReference() {
		return mutualReference;
	}
	/**
	 * @author david.dong
	 * @param mutualReference the mutualReference to set
	 */
	public void setMutualReference(ArrayList<NodeMutualReference> mutualReference) {
		this.mutualReference = mutualReference;
	}
	/**
	 * @author david.dong
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	
	
	
}
