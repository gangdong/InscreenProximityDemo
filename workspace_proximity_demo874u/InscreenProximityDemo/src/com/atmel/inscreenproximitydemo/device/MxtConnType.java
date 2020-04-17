package com.atmel.inscreenproximitydemo.device;
/**
 * project:InscreenProximityDemo <BR>
 * @author david.dong
 * create:2015年4月10日上午10:31:34
 */
public class MxtConnType {

	private String connType;
	private int refConunt;
	
	
	
	
	public MxtConnType() {
		this.connType = "1";
		this.refConunt = 255;
	}
	public String getConnType() {
		return connType;
	}
	public void setConnType(String connType) {
		this.connType = connType;
	}
	public int getRefConunt() {
		return refConunt;
	}
	public void setRefConunt(int refConunt) {
		this.refConunt = refConunt;
	}
	
	
}
