/**
 * project:InscreenProximityDemo <BR>
 * file name:T113Handler.java <BR>
 * @author david.dong
 * create:2015年2月26日下午9:48:05
 * 
 */
package com.atmel.inscreenproximitydemo.service;

import java.util.ArrayList;

import android.util.Log;

import com.atmel.inscreenproximitydemo.MaxtouchJni;
import com.atmel.inscreenproximitydemo.device.MxtDevice;
import com.atmel.inscreenproximitydemo.device.MxtObject;
import com.atmel.inscreenproximitydemo.utility.Utility;

/**
 * project:InscreenProximityDemo <BR>
 * class name:T113Handler <BR>
 * @author david.dong
 * create:2015年2月26日下午9:48:05
 */
public class T113Handler {

	private MaxtouchJni maxtouchJni = new MaxtouchJni();
	private Utility utility = new Utility();

	private int startPosition = -1;
	private int size = -1;
	private final int type = 113;
	
	
	/**
	 * constructor of class:T113Handler <BR>
	 * @author david.dong
	 * create:2015年2月26日下午9:49:04
	 */
	public T113Handler(MxtDevice mxtDevice) {
		
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
				Log.v("T113 message", "T113 init fail, no object found in mxt device!");
			}
		}
		else{
			
			Log.v("T113 message", "T113 init fail!");
		}
		
	}
	
	public boolean setSelfXGain(byte xGain){
		
		int ret;
		byte[] byteXGain = new byte[1];
		byteXGain[0] = xGain;
		
		ret = maxtouchJni.WriteRegister(startPosition+1, byteXGain);
		
		if(ret==1){
			
			Log.v("T113 message", "setSelfXGain() fail, i2c write fail!");
			return false;
		}
		else{
			Log.v("T113 message", "setSelfXGain() success!");
			return true;
		}
		
	}
	
	public byte getSelfXGain(){
		
		byte[] byteXGain = maxtouchJni.ReadRegister(startPosition+1, 1);
		return byteXGain[0];
	}
}
