package com.atmel.inscreenproximitydemo.device;
/**
 * project:InscreenProximityDemo <BR>
 * class name:MxtDevice <BR>
 * @author david.dong
 * create:2015年4月10日上午10:31:34
 */
public class MxtDevice {

	private MxtConnType mxtConnType;
	private MxtInfo mxtInfo;
	
	
	
	
	public MxtDevice() {
		this.mxtConnType = null;
		this.mxtInfo = null;
	}
	public MxtConnType getMxtConnType() {
		return mxtConnType;
	}
	public void setMxtConnType(MxtConnType mxtConnType) {
		this.mxtConnType = mxtConnType;
	}
	public MxtInfo getMxtInfo() {
		return mxtInfo;
	}
	public void setMxtInfo(MxtInfo mxtInfo) {
		this.mxtInfo = mxtInfo;
	}
	
	
	
	
}
