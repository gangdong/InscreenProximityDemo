/**
 * project:InscreenProximityDemo <BR>
 * file name:T38Handler.java <BR>
 * @author david.dong
 * create:2015年4月1日上午10:56:47
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
 * class name:T38Handler <BR>
 * @author david.dong
 * create:2015年4月1日上午10:56:47
 */
public class T38Handler {

	private MaxtouchJni maxtouchJni = new  MaxtouchJni();
	private Utility utility = new Utility();
	
	
	private int startPosition = -1;
	private int size = -1;
	private final int type = 38;
	/**
	 * constructor of class:T38Handler <BR>
	 * @author david.dong
	 * create:2015年4月1日上午10:58:17
	 */
	public T38Handler(MxtDevice mxtDevice) {
		
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
				Log.v("T38 message", "T38 init fail, no object found in mxt device!");
			}
		}
		else{
			
			Log.v("T38 message", "T38 init fail!");
		}
		
	}
	
	public boolean t38WriteValues(byte[] values, int index){
		
		int ret = 1;
		
		ret = maxtouchJni.WriteRegister(startPosition+index, values);
		
		if(ret == 1){
			Log.v("T38 message", " t38WriteValues() fail, i2c write fail!");
			return false;
		}else{
			Log.v("T38 message", " t38WriteValues() succeed!");
			return true;
		}
		
	}
	
	public byte[] t38ReadValues(int index, int size){
		
		byte[] t38Register = new byte[size];
		
		if(startPosition == -1){
			Log.v("T38 message", " t38ReadValues() fail, start position is invalid!");
			//return false;
		}
		
		if(size == -1){
			Log.v("T38 message", " t38ReadValues() fail, size is invalid!");
			//return false;
			
		}
		
		t38Register = maxtouchJni.ReadRegister(startPosition+index, size);
		
		Log.v("T38 message", " t38ReadValues() succeed!");
		return t38Register;
	}
}
