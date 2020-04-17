/**
 * project:InscreenProximityDemo <BR>
 * file name:T6Command.java <BR>
 * @author david.dong
 * create:2015年2月17日下午9:02:56
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
 * class name:T6Command <BR>
 * @author david.dong
 * create:2015年2月17日下午9:02:56
 */
public class T6Handler {
	
	private MaxtouchJni maxtouchJni = new  MaxtouchJni();
	private Utility utility = new Utility();
	
	
	private int startPosition = -1;
	private int size = -1;
	private final int type = 6;
	
	
	private byte[] mutualDeltaCmd = new byte[7];
	private byte[] mutualRefCmd = new byte[7];
	private byte[] selfDeltaCmd = new byte[7];
	private byte[] selfRefCmd = new byte[7];
	private byte[] pageUpCmd = new byte[7];
	private byte[] pageDownCmd = new byte[7];
	private byte[] calibrateCmd = new byte[7];
	private byte[] backupCmd_01 = new byte[7];
	private byte[] backupCmd_02 = new byte[7];
	private byte[] backupCmd_03 = new byte[7];
	private byte[] resetCmd = new byte[7];
	private byte[] bootloaderCmd = new byte[7];
	
	private  final byte byteMutualDelta = 0x10;
	private  final byte byteMutualRef = 0x11;
	private  final byte bytePageUp = 0x01;
	private  final byte bytePageDown = 0x02;
	private  final byte byteSelfDelta = (byte) 0xf7;
	private  final byte byteSelfRef = (byte) 0xf8;
	
	private  final byte byteCalibrate = 0x01;
	private  final byte byteBackup_01 = 0x55;
	private  final byte byteBackup_02 = 0x33;
	private  final byte byteBackup_03 = 0x44;
	private  final byte byteReset = 0x01;
	private  final byte byteBootloader = (byte) 0xA5;
	
	/**
	 * constructor of class:T6Command <BR>
	 * @author david.dong
	 * create:2015年2月17日下午9:06:15
	 */
	public T6Handler(MxtDevice mxtDevice) {
		
		/* comment our for save ram space, it is useless for new code
		mutualDeltaCmd[0] = 0;
		mutualDeltaCmd[1] = 0;
		mutualDeltaCmd[2] = 0;
		mutualDeltaCmd[3] = 0;
		mutualDeltaCmd[4] = 0;
		mutualDeltaCmd[5] = byteMutualDelta;
		mutualDeltaCmd[6] = 0;
		
		mutualRefCmd[0] = 0;
		mutualRefCmd[1] = 0;
		mutualRefCmd[2] = 0;
		mutualRefCmd[3] = 0;
		mutualRefCmd[4] = 0;
		mutualRefCmd[5] = byteMutualRef;
		mutualRefCmd[6] = 0;
		
		selfDeltaCmd[0] = 0;
		selfDeltaCmd[1] = 0;
		selfDeltaCmd[2] = 0;
		selfDeltaCmd[3] = 0;
		selfDeltaCmd[4] = 0;
		selfDeltaCmd[5] = byteSelfDelta;
		selfDeltaCmd[6] = 0;
		
		selfRefCmd[0] = 0;
		selfRefCmd[1] = 0;
		selfRefCmd[2] = 0;
		selfRefCmd[3] = 0;
		selfRefCmd[4] = 0;
		selfRefCmd[5] = byteSelfRef;
		selfRefCmd[6] = 0;
		
		
		calibrateCmd[0] = 0;
		calibrateCmd[1] = 0;
		calibrateCmd[2] = byteCalibrate;
		calibrateCmd[3] = 0;
		calibrateCmd[4] = 0;
		calibrateCmd[5] = 0;
		calibrateCmd[6] = 0;
		
		backupCmd_01[0] = 0;
		backupCmd_01[1] = byteBackup_01;
		backupCmd_01[2] = 0;
		backupCmd_01[3] = 0;
		backupCmd_01[4] = 0;
		backupCmd_01[5] = 0;
		backupCmd_01[6] = 0;
		
		backupCmd_02[0] = 0;
		backupCmd_02[1] = byteBackup_02;
		backupCmd_02[2] = 0;
		backupCmd_02[3] = 0;
		backupCmd_02[4] = 0;
		backupCmd_02[5] = 0;
		backupCmd_02[6] = 0;
		
		backupCmd_03[0] = 0;
		backupCmd_03[1] = byteBackup_03;
		backupCmd_03[2] = 0;
		backupCmd_03[3] = 0;
		backupCmd_03[4] = 0;
		backupCmd_03[5] = 0;
		backupCmd_03[6] = 0;
		
		
		resetCmd[0] = byteReset;
		resetCmd[1] = 0;
		resetCmd[2] = 0;
		resetCmd[3] = 0;
		resetCmd[4] = 0;
		resetCmd[5] = 0;
		resetCmd[6] = 0;
		
		bootloaderCmd[0] = byteBootloader;
		bootloaderCmd[1] = 0;
		bootloaderCmd[2] = 0;
		bootloaderCmd[3] = 0;
		bootloaderCmd[4] = 0;
		bootloaderCmd[5] = 0;
		bootloaderCmd[6] = 0;
		*/
		
		if(mxtDevice.getMxtInfo()!=null){
			
			ArrayList<MxtObject> objects = mxtDevice.getMxtInfo().getMxtObjects();
			int index = utility.findIndexOnObjectsByType(mxtDevice, type);
			
			if(index != -1){
				
				int startPosMsb = objects.get(index).getStartPosMsb();
				int startPosLsb = objects.get(index).getStartPosLsb();
				
				this.startPosition = (startPosLsb & 0xff)|((startPosMsb & 0xff)<<8);
				
				this.size = objects.get(index).getSize();
			}
			else{
				Log.v("T6 message", "T6 init fail, no object found in mxt device!");
			}
		}
		else{
			Log.v("T6 message", "T6 init fail!");
		}
		
	}
	
	
	
	public boolean t6Backup(){
		
		int ret = 1;
		byte[] t6RegisterByte1 = new byte[1];
		
		if(this.startPosition == -1)
		{
			Log.v("T6 message", " t6Backup() fail, start position is invalid!");
			return false;
		}

		byte[] byteBackupCmd = new byte[1];
		byteBackupCmd[0] = byteBackup_01;
		ret = maxtouchJni.WriteRegister(this.startPosition+1, byteBackupCmd);
		
		if(ret==1){
			
			Log.v("T6 message", "t6Backup() fail, i2c write fail!");
			return false;
		}
		else{
			
			if(this.size == -1){
				Log.v("T6 message", "t6Backup() fail, size is invalid!");
				return false;
			}
			
			int retryCount = 0;

			t6RegisterByte1 = maxtouchJni.ReadRegister(this.startPosition+1, 1);
			while((t6RegisterByte1[0]!=0)&&(retryCount<100)){
				t6RegisterByte1 = maxtouchJni.ReadRegister(this.startPosition+1, 1);
				Log.v("T6 message", "t6Backup() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			
			if(retryCount < 100){
				Log.v("T6 message", "t6Backup() succeed,retry count "+String.valueOf(retryCount)+"!");
				return true;
			}
			else{
				Log.v("T6 message", "t6Backup() fail!");
				return false;
			}
		}
		
	}
	
	
	/**
	 * @author david.dong
	 * @return true if execution success
	 */
	public boolean t6Calibrate(){
		
		int ret = 1;
		byte[] t6RegisterByte2 = new byte[1];
		
		if(this.startPosition == -1)
		{
			Log.v("T6 message", " t6Calibrate() fail, start position is invalid!");
			return false;
		}

		byte[] byteCablibrateCmd = new byte[1];
		byteCablibrateCmd[0] = byteCalibrate;
		ret = maxtouchJni.WriteRegister(this.startPosition+2, byteCablibrateCmd);
		
		if(ret==1){
			
			Log.v("T6 message", "t6Calibrate() fail, i2c write fail!");
			return false;
		}
		else{
			
			if(this.size == -1){
				Log.v("T6 message", "t6Calibrate() fail, size is invalid!");
				return false;
			}
			
			int retryCount = 0;

			t6RegisterByte2 = maxtouchJni.ReadRegister(this.startPosition+2, 1);
			while((t6RegisterByte2[0]!=0)&&(retryCount<100)){
				t6RegisterByte2 = maxtouchJni.ReadRegister(this.startPosition+2, 1);
				Log.v("T6 message", "t6Calibrate() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			
			if(retryCount < 100){
				Log.v("T6 message", "t6Calibrate() succeed,retry count "+String.valueOf(retryCount)+"!");
				return true;
			}
			else{
				Log.v("T6 message", "t6Calibrate() fail!");
				return false;
			}
		}
	}
	
	/**
	 * @author david.dong
	 * @return true if execution success
	 */
	public boolean t6MutualReference(){
		
		int ret = 1;
		byte[] t6RegisterByte5 = new byte[1];
		
		if(this.startPosition == -1)
		{
			Log.v("T6 message", " t6MutualReference() fail, start position is invalid!");
			return false;
		}

		byte[] byteMutualReferenceCmd = new byte[1];
		byteMutualReferenceCmd[0] = byteMutualRef;
		ret = maxtouchJni.WriteRegister(this.startPosition+5, byteMutualReferenceCmd);
		
		if(ret==1){
			
			Log.v("T6 message", "t6MutualReference() fail, i2c write fail!");
			return false;
		}
		else{
			
			if(this.size == -1){
				Log.v("T6 message", "t6MutualReference() fail, size is invalid!");
				return false;
			}
			
			int retryCount = 0;

			t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
			while((t6RegisterByte5[0]!=0)&&(retryCount<100)){
				t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
				Log.v("T6 message", "t6MutualReference() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			
			if(retryCount < 100){
				Log.v("T6 message", "t6MutualReference() succeed,retry count "+String.valueOf(retryCount)+"!");
				return true;
			}
			else{
				Log.v("T6 message", "t6MutualReference() fail!");
				return false;
			}
		}
	}
	
	/**
	 * @author david.dong
	 * @return true if execution success
	 */
	public boolean t6SelfReference(){
		
		int ret = 1;
		byte[] t6RegisterByte5 = new byte[1];
		
		if(this.startPosition == -1)
		{
			Log.v("T6 message", " t6SelfReference() fail, start position is invalid!");
			return false;
		}

		byte[] byteSelfReferenceCmd = new byte[1];
		byteSelfReferenceCmd[0] = byteSelfRef;
		ret = maxtouchJni.WriteRegister(this.startPosition+5, byteSelfReferenceCmd);
		
		if(ret==1){
			
			Log.v("T6 message", "t6SelfReference() fail, i2c write fail!");
			return false;
		}
		else{
			
			if(this.size == -1){
				Log.v("T6 message", "t6SelfReference() fail, size is invalid!");
				return false;
			}
			
			int retryCount = 0;

			t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
			while((t6RegisterByte5[0]!=0)&&(retryCount<100)){
				t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
				Log.v("T6 message", "t6SelfReference() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			
			if(retryCount < 100){
				Log.v("T6 message", "t6SelfReference() succeed,retry count "+String.valueOf(retryCount)+"!");
				return true;
			}
			else{
				Log.v("T6 message", "t6SelfReference() fail!");
				return false;
			}
		}
	}
	
	/**
	 * @author david.dong
	 * @return true if execution success
	 */
	public boolean t6SelfDelta(){
		
		int ret = 1;
		byte[] t6RegisterByte5 = new byte[1];
		
		/*comment out to speedup execution speed by reducing unnecessary read bytes
		byte[] t6Register = new byte[size+1];
		*/
		
		if(this.startPosition == -1)
		{
			Log.v("T6 message", " t6SelfDelta() fail, start position is invalid!");
			return false;
		}
		
		/*comment out to speedup execution speed by reducing unnecessary write bytes
		ret = maxtouchJni.WriteRegister(this.startPosition, selfDeltaCmd);
		*/
		
		byte[] byteSelfDeltaCmd = new byte[1];
		byteSelfDeltaCmd[0] = byteSelfDelta;
		ret = maxtouchJni.WriteRegister(this.startPosition+5, byteSelfDeltaCmd);
		
		if(ret==1){
			
			Log.v("T6 message", "t6SelfDelta() fail, i2c write fail!");
			return false;
		}
		else{
			
			if(this.size == -1){
				Log.v("T6 message", "t6SelfDelta() fail, size is invalid!");
				return false;
			}
			
			int retryCount = 0;
			/* comment out to speedup execution speed by reducing unnecessary read bytes
			t6Register = maxtouchJni.ReadRegister(this.startPosition, this.size+1);
			while((t6Register[5]!=0)&&(retryCount<100)){
				t6Register = maxtouchJni.ReadRegister(this.startPosition, this.size+1);
				Log.v("T6 message", "t6SelfDelta() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			*/
			t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
			while((t6RegisterByte5[0]!=0)&&(retryCount<100)){
				t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
				Log.v("T6 message", "t6SelfDelta() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			
			if(retryCount < 100){
				Log.v("T6 message", "t6SelfDelta() succeed,retry count "+String.valueOf(retryCount)+"!");
				return true;
			}
			else{
				Log.v("T6 message", "t6SelfDelta() fail!");
				return false;
			}
		}
	}
	
	/**
	 * @author david.dong
	 * @return true if execution success
	 */
	public boolean t6MutualDelta(){
		
		int ret = 1;
		/*comment out to speedup execution speed by reducing unnecessary read bytes
		byte[] t6Register = new byte[size+1];
		*/
		
		byte[] t6RegisterByte5 = new byte[1];
		
		if(this.startPosition == -1)
		{
			Log.v("T6 message", " t6MutualDelta() fail, start position is invalid!");
			return false;
		}
		
		/*comment out to speedup execution speed by reducing unnecessary write bytes
		ret = maxtouchJni.WriteRegister(this.startPosition, mutualDeltaCmd);
		*/
		byte[] byteMutualDeltaCmd = new byte[1];
		byteMutualDeltaCmd[0] = byteMutualDelta;
		
		ret = maxtouchJni.WriteRegister(this.startPosition+5, byteMutualDeltaCmd);
		
		if(ret==1){
			
			Log.v("T6 message", "t6MutualDelta() fail, i2c write fail!");
			return false;
		}
		else{
			
			if(this.size == -1){
				Log.v("T6 message", "t6MutualDelta() fail, size is invalid!");
				return false;
			}
			
			int retryCount = 0;
			/*comment out to speedup execution speed by reducing unnecessary write bytes
			t6Register = maxtouchJni.ReadRegister(this.startPosition, this.size+1);
			while((t6Register[5]!=0)&&(retryCount<100)){
				t6Register = maxtouchJni.ReadRegister(this.startPosition, this.size+1);
				Log.v("T6 message", "t6MutualDelta() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			*/
			t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
			while((t6RegisterByte5[0]!=0)&&(retryCount<100)){
				t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
				Log.v("T6 message", "t6MutualDelta() retry...,retry count "+String.valueOf(retryCount)+"!");
				retryCount++;
			}
			
			if(retryCount < 100){
				Log.v("T6 message", "t6MutualDelta() succeed,retry count "+String.valueOf(retryCount)+"!");
				return true;
			}
			else{
				Log.v("T6 message", "t6MutualDelta() fail!");
				return false;
			}
		}
	}
	
	/**
	 * @author david.dong
	 * @return true if execution success
	 */
	public boolean t6PageUp(){
		
		int ret = 1;
		
		/*comment out for speed up i2c read/write by reducing unnecessary write bytes
		byte[] t6Register = new byte[size+1];
		pageUpCmd[0] = 0;
		pageUpCmd[1] = 0;
		pageUpCmd[2] = 0;
		pageUpCmd[3] = 0;
		pageUpCmd[4] = 0;
		pageUpCmd[5] = bytePageUp;
		pageUpCmd[6] = 0;
		*/
		
		
		if(this.startPosition == -1)
		{
			Log.v("T6 message", " t6Pageup() fail, start position is invalid!");
			return false;
		}
		
		/*comment out for speed up i2c read/write by reducing unnecessary write bytes
		ret = maxtouchJni.WriteRegister(this.startPosition, pageUpCmd);
		*/
		
		byte[] bytePageUpCmd = new byte[1];
		bytePageUpCmd[0] = bytePageUp;
		
		ret = maxtouchJni.WriteRegister(this.startPosition+5, bytePageUpCmd);
		
		if(ret==1){
			
			Log.v("T6 message", "t6Pageup() fail, i2c write fail!");
			return false;
		}
		else{
			
			if(this.size == -1){
				Log.v("T6 message", "t6Pageup() fail, size is invalid!");
				return false;
			}
			
			int retryCount = 0;
			
			byte[] t6RegisterByte5 = new byte[1];
			
			/*comment out for speed up i2c read/write by reducing unnecessary read bytes
			t6Register = maxtouchJni.ReadRegister(this.startPosition, this.size+1);
			while((t6Register[5]!=0)&&(retryCount<100)){
				t6Register = maxtouchJni.ReadRegister(this.startPosition, this.size+1);
				Log.v("T6 message", "t6Pageup() retry..., retry count "+ String.valueOf(retryCount)+"!");
				retryCount++;
			}
			*/
			
			t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
			while((t6RegisterByte5[0]!=0)&&(retryCount<100)){
				t6RegisterByte5 = maxtouchJni.ReadRegister(this.startPosition+5, 1);
				Log.v("T6 message", "t6Pageup() retry..., retry count "+ String.valueOf(retryCount)+"!");
				retryCount++;
			}
			
			if(retryCount<100){
				Log.v("T6 message", "t6Pageup() succeed, retry count "+String.valueOf(retryCount));
				return true;
			}else{
				Log.v("T6 message", "t6Pageup() fail!");
				return false;
			}
			
		}
		
	}
	
	/**
	 * @author david.dong
	 * @return the mutualDeltaCmd
	 */
	public byte[] getMutualDeltaCmd() {
		return mutualDeltaCmd;
	}
	/**
	 * @author david.dong
	 * @param mutualDeltaCmd the mutualDeltaCmd to set
	 */
	public void setMutualDeltaCmd(byte[] mutualDeltaCmd) {
		this.mutualDeltaCmd = mutualDeltaCmd;
	}
	/**
	 * @author david.dong
	 * @return the mutualRefCmd
	 */
	public byte[] getMutualRefCmd() {
		return mutualRefCmd;
	}
	/**
	 * @author david.dong
	 * @param mutualRefCmd the mutualRefCmd to set
	 */
	public void setMutualRefCmd(byte[] mutualRefCmd) {
		this.mutualRefCmd = mutualRefCmd;
	}
	/**
	 * @author david.dong
	 * @return the selfDeltaCmd
	 */
	public byte[] getSelfDeltaCmd() {
		return selfDeltaCmd;
	}
	/**
	 * @author david.dong
	 * @param selfDeltaCmd the selfDeltaCmd to set
	 */
	public void setSelfDeltaCmd(byte[] selfDeltaCmd) {
		this.selfDeltaCmd = selfDeltaCmd;
	}
	/**
	 * @author david.dong
	 * @return the selfRefCmd
	 */
	public byte[] getSelfRefCmd() {
		return selfRefCmd;
	}
	/**
	 * @author david.dong
	 * @param selfRefCmd the selfRefCmd to set
	 */
	public void setSelfRefCmd(byte[] selfRefCmd) {
		this.selfRefCmd = selfRefCmd;
	}
	/**
	 * @author david.dong
	 * @return the calibrateCmd
	 */
	public byte[] getCalibrateCmd() {
		return calibrateCmd;
	}
	/**
	 * @author david.dong
	 * @param calibrateCmd the calibrateCmd to set
	 */
	public void setCalibrateCmd(byte[] calibrateCmd) {
		this.calibrateCmd = calibrateCmd;
	}
	/**
	 * @author david.dong
	 * @return the resetCmd
	 */
	public byte[] getResetCmd() {
		return resetCmd;
	}
	/**
	 * @author david.dong
	 * @param resetCmd the resetCmd to set
	 */
	public void setResetCmd(byte[] resetCmd) {
		this.resetCmd = resetCmd;
	}
	/**
	 * @author david.dong
	 * @return the backupCmd_01
	 */
	public byte[] getBackupCmd_01() {
		return backupCmd_01;
	}
	/**
	 * @author david.dong
	 * @param backupCmd_01 the backupCmd_01 to set
	 */
	public void setBackupCmd_01(byte[] backupCmd_01) {
		this.backupCmd_01 = backupCmd_01;
	}
	/**
	 * @author david.dong
	 * @return the backupCmd_02
	 */
	public byte[] getBackupCmd_02() {
		return backupCmd_02;
	}
	/**
	 * @author david.dong
	 * @param backupCmd_02 the backupCmd_02 to set
	 */
	public void setBackupCmd_02(byte[] backupCmd_02) {
		this.backupCmd_02 = backupCmd_02;
	}
	/**
	 * @author david.dong
	 * @return the backupCmd_03
	 */
	public byte[] getBackupCmd_03() {
		return backupCmd_03;
	}
	/**
	 * @author david.dong
	 * @param backupCmd_03 the backupCmd_03 to set
	 */
	public void setBackupCmd_03(byte[] backupCmd_03) {
		this.backupCmd_03 = backupCmd_03;
	}
	/**
	 * @author david.dong
	 * @return the bootloaderCmd
	 */
	public byte[] getBootloaderCmd() {
		return bootloaderCmd;
	}
	/**
	 * @author david.dong
	 * @param bootloaderCmd the bootloaderCmd to set
	 */
	public void setBootloaderCmd(byte[] bootloaderCmd) {
		this.bootloaderCmd = bootloaderCmd;
	}
	
	
	
	
}
