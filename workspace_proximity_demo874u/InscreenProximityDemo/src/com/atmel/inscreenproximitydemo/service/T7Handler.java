/**
 * project:InscreenProximityDemo <BR>
 * file name:T7Handler.java <BR>
 * @author david.dong
 * create:2015年2月24日下午8:29:09
 * 
 */
package com.atmel.inscreenproximitydemo.service;

import java.util.ArrayList;

import android.util.Log;

import com.atmel.inscreenproximitydemo.MaxtouchJni;
import com.atmel.inscreenproximitydemo.datatype.PowerConfigData;
import com.atmel.inscreenproximitydemo.device.MxtDevice;
import com.atmel.inscreenproximitydemo.device.MxtObject;
import com.atmel.inscreenproximitydemo.utility.Utility;

/**
 * project:InscreenProximityDemo <BR>
 * class name:T7Handler <BR>
 * @author david.dong
 * create:2015年2月24日下午8:29:09
 */
public class T7Handler {

	MaxtouchJni maxtouchJni = new MaxtouchJni();
	private Utility utility = new Utility();
	private int startPosition = -1;
	private int size = -1;
	private final int type = 7;
	

	/**
	 * constructor of class:T7Handler <BR>
	 * @author david.dong
	 * create:2015年2月24日下午8:33:53
	 * @param mxtDevice
	 */
	public T7Handler(MxtDevice mxtDevice) {
		
		if (mxtDevice.getMxtInfo() != null) {

			ArrayList<MxtObject> objects = mxtDevice.getMxtInfo()
					.getMxtObjects();
			int index = utility.findIndexOnObjectsByType(mxtDevice, type);

			if (index != -1) {

				int startPosMsb = objects.get(index).getStartPosMsb();
				int startPosLsb = objects.get(index).getStartPosLsb();

				this.startPosition = (startPosLsb & 0xff)
						| ((startPosMsb & 0xff) << 8);

				this.size = objects.get(index).getSize();
			}
			else{
				Log.v("T7 message", "T7 init fail, no object found in mxt device!");
			}
		}
		else{
			
			Log.v("T7 message", "T7 init fail!");
		}
		
	}

	public boolean t7ReadPowerConfig(PowerConfigData powerConfigData){
		
		byte[] t7Register = new byte[size+1];
		
		if(startPosition == -1){
			Log.v("T7 message", " t7ReadPowerConfig() fail, start position is invalid!");
			return false;
		}
		
		if(size == -1){
			Log.v("T7 message", " t7ReadPowerConfig() fail, size is invalid!");
			return false;
			
		}
		
		t7Register = maxtouchJni.ReadRegister(startPosition, size+1);
		
		powerConfigData.setIdleAcq(t7Register[0]);
		powerConfigData.setActiveAcq(t7Register[1]);
		powerConfigData.setActiveToIdleTimeOut(t7Register[2]);
		powerConfigData.setCfg(t7Register[3]);
		powerConfigData.setCfg2(t7Register[4]);

		Log.v("T7 message", " t7ReadPowerConfig() succeed!");
		return true;
	}
	
	public boolean t7WritePowerConfig(PowerConfigData powerConfigData){
		
		int ret = 1;
		byte[] powerConfigCmd = new byte[size+1];
		
		powerConfigCmd[0] = powerConfigData.getIdleAcq();
		powerConfigCmd[1] = powerConfigData.getActiveAcq();
		powerConfigCmd[2] = powerConfigData.getActiveToIdleTimeOut();
		powerConfigCmd[3] = powerConfigData.getCfg();
		powerConfigCmd[4] = powerConfigData.getCfg2();
		
		ret = maxtouchJni.WriteRegister(startPosition, powerConfigCmd);
		
		if(ret == 1){
			Log.v("T7 message", " t7WritePowerConfig() fail, i2c write fail!");
			return false;
		}else{
			Log.v("T7 message", " t7WritePowerConfig() succeed!");
			return true;
		}
	}
}
