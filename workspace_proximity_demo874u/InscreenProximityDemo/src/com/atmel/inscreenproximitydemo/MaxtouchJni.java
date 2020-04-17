package com.atmel.inscreenproximitydemo;

import com.atmel.inscreenproximitydemo.device.MxtDevice;
import com.atmel.inscreenproximitydemo.device.MxtIdInfo;

public class MaxtouchJni {
	public native boolean Scan();
	//public native String getName();
	public native boolean GetInfo();
	
	public native int GetInfoDebug();
	
	public native String GetSysfsPath();
	
	public native String GetSysfsDirectory();
	
	public native byte[] ReadRegister(int start_register,int count);
	
	public native int WriteRegister(int start_register,byte[] data);
	
	public native String  loadMxtDevice(MxtDevice mxtDevice);
	
	
}
