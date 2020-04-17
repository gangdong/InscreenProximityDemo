/**
 * project:InscreenProximityDemo <BR>
 * file name:ConstantFactory.java <BR>
 * @author david.dong
 * create:2015年2月16日下午10:32:28
 * 
 */
package com.atmel.inscreenproximitydemo;

import java.util.Timer;

import com.atmel.inscreenproximitydemo.datatype.HoverDelta;
import com.atmel.inscreenproximitydemo.datatype.MutualDelta;
import com.atmel.inscreenproximitydemo.datatype.MutualReference;
import com.atmel.inscreenproximitydemo.datatype.SelfDelta;
import com.atmel.inscreenproximitydemo.datatype.SelfReference;
import com.atmel.inscreenproximitydemo.datatype.SingleEndDelta;
import com.atmel.inscreenproximitydemo.datatype.SingleEndReference;
import com.atmel.inscreenproximitydemo.device.MxtDevice;

/**
 * project:InscreenProximityDemo <BR>
 * class name:ConstantFactory <BR>
 * @author david.dong
 * create:2015年2月16日下午10:32:28
 */
public class ConstantFactory {

	public static Timer nTimer = new Timer();
	
	//public static boolean isProBtnClicked = false;
	
	// flags for read execution status
	public static boolean isReadAllSelfDeltaDone = false;
	
	public static boolean isReadSelfSeDeltaDone = false;
	
	public static boolean isReadSEReferenceDone = false;
	
	public static boolean isReadSEDeltaDone = false;
	
	public static boolean isReadMutualDeltaDone = false;
	
	public static boolean isReadMutualReferenceDone = false;
	
	public static boolean isReadSelfDeltaDone = false;
	
	public static boolean isReadSelfReferenceDone = false;
	
	// global mxt device instance
	public static MxtDevice mxtDevice = new MxtDevice();
	
	// global data
	public static SelfReference selfReference = new SelfReference();
	
	public static SelfDelta selfDelta = new SelfDelta();
	
	public static HoverDelta hoverDelta = new HoverDelta();
	
	public static MutualDelta mutualDelta = new MutualDelta();
	
	public static MutualReference mutualReference = new MutualReference();
	
	public static SingleEndReference singleEndReference = new SingleEndReference();
	
	public static SingleEndDelta singleEndDelta = new SingleEndDelta();
	
	// define byte number occupied by every node
	public static final int bytesPerNode = 2;
}
