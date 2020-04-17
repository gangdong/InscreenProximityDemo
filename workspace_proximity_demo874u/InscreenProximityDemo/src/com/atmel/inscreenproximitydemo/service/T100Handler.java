/**
 * project:InscreenProximityDemo <BR>
 * file name:T100Handler.java <BR>
 * @author david.dong
 * create:2015年2月19日下午10:57:16
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
 * class name:T100Handler <BR>
 * 
 * @author david.dong create:2015年2月19日下午10:57:16
 */
public class T100Handler {

	private MaxtouchJni maxtouchJni = new MaxtouchJni();
	private Utility utility = new Utility();

	private int startPosition = -1;
	private int size = -1;
	private final int type = 100;

	/**
	 * constructor of class:T100Handler <BR>
	 * 
	 * @author david.dong create:2015年2月19日下午11:40:48
	 */
	public T100Handler(MxtDevice mxtDevice) {

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
			} else {
				Log.v("T100 message",
						"T100 init fail, no object found in mxt device!");
			}
		} else {

			Log.v("T100 message", "T100 init fail!");
		}
	}

	public int getXSize() {

		int rtn = -1;

		if (this.startPosition == -1) {
			Log.v("T100 message", "getXSize() fail, start position is invalid!");
			return rtn;
		}
		if (this.size == -1) {
			Log.v("T100 message", "getXSize() fail, size is invalid!");
			return rtn;
		}
		byte[] tmpData = maxtouchJni.ReadRegister(startPosition, size+1);
		
		rtn = tmpData[9];
		
		return rtn;
	}

	public int getYSize(){
		
		int rtn = -1;

		if (this.startPosition == -1) {
			Log.v("T100 message", "getYSize() fail, start position is invalid!");
			return rtn;
		}
		if (this.size == -1) {
			Log.v("T100 message", "getYSize() fail, size is invalid!");
			return rtn;
		}
		byte[] tmpData = maxtouchJni.ReadRegister(startPosition, size+1);
		
		rtn = tmpData[20];
		
		return rtn;
		
	}
}
