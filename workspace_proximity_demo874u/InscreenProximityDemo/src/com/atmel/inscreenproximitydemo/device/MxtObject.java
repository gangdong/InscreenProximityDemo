package com.atmel.inscreenproximitydemo.device;
/**
 * project:InscreenProximityDemo <BR>
 * class name:MxtObject <BR>
 * @author david.dong
 * create:2015年4月10日上午10:31:34
 */
public class MxtObject {
	private int type;
	private int startPosLsb;
	private int startPosMsb;
	private int size;
	private int instances;
	private int numberReport;
	
	
	
	
	public MxtObject() {
		this.type = 255;
		this.startPosLsb = 255;
		this.startPosMsb = 255;
		this.size = 255;
		this.instances = 255;
		this.numberReport = 255;
	}




	public int getType() {
		return type;
	}




	public void setType(int type) {
		this.type = type;
	}




	public int getStartPosLsb() {
		return startPosLsb;
	}




	public void setStartPosLsb(int startPosLsb) {
		this.startPosLsb = startPosLsb;
	}




	public int getStartPosMsb() {
		return startPosMsb;
	}




	public void setStartPosMsb(int startPosMsb) {
		this.startPosMsb = startPosMsb;
	}




	public int getSize() {
		return size;
	}




	public void setSize(int size) {
		this.size = size;
	}




	public int getInstances() {
		return instances;
	}




	public void setInstances(int instances) {
		this.instances = instances;
	}




	public int getNumberReport() {
		return numberReport;
	}




	public void setNumberReport(int numberReport) {
		this.numberReport = numberReport;
	}
	
	
	
	
	
	
	
}
