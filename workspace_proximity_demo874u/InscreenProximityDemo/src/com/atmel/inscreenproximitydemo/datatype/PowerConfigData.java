/**
 * project:InscreenProximityDemo <BR>
 * file name:PowerConfigData.java <BR>
 * @author david.dong
 * create:2015年2月24日下午9:13:26
 * 
 */
package com.atmel.inscreenproximitydemo.datatype;

/**
 * project:InscreenProximityDemo <BR>
 * class name:PowerConfigData <BR>
 * @author david.dong
 * create:2015年2月24日下午9:13:26
 */
public class PowerConfigData {

	private byte idleAcq = 0;
	private byte activeAcq = 0;
	private byte activeToIdleTimeOut = 0;
	
	private byte cfg = 0;
	private byte cfg2 = 02;
	/**
	 * @author david.dong
	 * @return the idleAcq
	 */
	public byte getIdleAcq() {
		return idleAcq;
	}
	/**
	 * @author david.dong
	 * @param idleAcq the idleAcq to set
	 */
	public void setIdleAcq(byte idleAcq) {
		this.idleAcq = idleAcq;
	}
	/**
	 * @author david.dong
	 * @return the activeAcq
	 */
	public byte getActiveAcq() {
		return activeAcq;
	}
	/**
	 * @author david.dong
	 * @param activeAcq the activeAcq to set
	 */
	public void setActiveAcq(byte activeAcq) {
		this.activeAcq = activeAcq;
	}
	/**
	 * @author david.dong
	 * @return the activeToIdleTimeOut
	 */
	public byte getActiveToIdleTimeOut() {
		return activeToIdleTimeOut;
	}
	/**
	 * @author david.dong
	 * @param activeToIdleTimeOut the activeToIdleTimeOut to set
	 */
	public void setActiveToIdleTimeOut(byte activeToIdleTimeOut) {
		this.activeToIdleTimeOut = activeToIdleTimeOut;
	}
	/**
	 * @author david.dong
	 * @return the cfg
	 */
	public byte getCfg() {
		return cfg;
	}
	/**
	 * @author david.dong
	 * @param cfg the cfg to set
	 */
	public void setCfg(byte cfg) {
		this.cfg = cfg;
	}
	/**
	 * @author david.dong
	 * @return the cfg2
	 */
	public byte getCfg2() {
		return cfg2;
	}
	/**
	 * @author david.dong
	 * @param cfg2 the cfg2 to set
	 */
	public void setCfg2(byte cfg2) {
		this.cfg2 = cfg2;
	}
	
	
	
}
