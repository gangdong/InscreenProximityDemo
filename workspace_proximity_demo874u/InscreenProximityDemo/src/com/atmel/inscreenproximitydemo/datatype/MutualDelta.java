/**
 * project:InscreenProximityDemo <BR>
 * file name:MutualDelta.java <BR>
 * @author david.dong
 * create:2015年2月19日下午8:17:45
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

import java.util.ArrayList;

import com.atmel.inscreenproximitydemo.device.MxtDevice;

/**
 * project:InscreenProximityDemo <BR>
 * class name:MutualDelta <BR>
 * @author david.dong
 * create:2015年2月19日下午8:17:45
 */
public class MutualDelta {

	private ArrayList<NodeMutualDelta> rawDelta = new ArrayList<NodeMutualDelta>();
	
	private int type;
	
	//private int page;
	

	/**
	 * constructor of class:MutualDelta <BR>
	 * @author david.dong
	 * create:2015年2月19日下午8:20:31
	 */
	public MutualDelta() {
		
		this.type = 16;
	}

	public void loadPara(MxtDevice mxtDevice){
		
		
	}

	/**
	 * @author david.dong
	 * @return the rawDelta
	 */
	public ArrayList<NodeMutualDelta> getRawDelta() {
		return rawDelta;
	}

	/**
	 * @author david.dong
	 * @param rawDelta the rawDelta to set
	 */
	public void setRawDelta(ArrayList<NodeMutualDelta> rawDelta) {
		this.rawDelta = rawDelta;
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
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
}
