/**
 * project:InscreenProximityDemo <BR>
 * file name:T37DataHandler.java <BR>
 * @author david.dong
 * create:2015年2月17日下午8:39:43
 * 
 */
package com.atmel.inscreenproximitydemo.service;

import java.util.ArrayList;

import android.graphics.Point;
import android.util.Log;

import com.atmel.inscreenproximitydemo.ConstantFactory;
import com.atmel.inscreenproximitydemo.MaxtouchJni;
import com.atmel.inscreenproximitydemo.datatype.HoverDelta;
import com.atmel.inscreenproximitydemo.datatype.MutualDelta;
import com.atmel.inscreenproximitydemo.datatype.MutualReference;
import com.atmel.inscreenproximitydemo.datatype.NodeMutualDelta;
import com.atmel.inscreenproximitydemo.datatype.NodeMutualReference;
import com.atmel.inscreenproximitydemo.datatype.SelfDelta;
import com.atmel.inscreenproximitydemo.datatype.SelfReference;
import com.atmel.inscreenproximitydemo.datatype.SingleEndDelta;
import com.atmel.inscreenproximitydemo.datatype.SingleEndReference;
import com.atmel.inscreenproximitydemo.datatype.XHoverDelta;
import com.atmel.inscreenproximitydemo.datatype.XSelfDelta;
import com.atmel.inscreenproximitydemo.datatype.XSelfReference;
import com.atmel.inscreenproximitydemo.datatype.XSingleEndDelta;
import com.atmel.inscreenproximitydemo.datatype.XSingleEndReference;
import com.atmel.inscreenproximitydemo.datatype.YHoverDelta;
import com.atmel.inscreenproximitydemo.datatype.YSelfDelta;
import com.atmel.inscreenproximitydemo.datatype.YSelfReference;
import com.atmel.inscreenproximitydemo.datatype.YSingleEndDelta;
import com.atmel.inscreenproximitydemo.device.MxtDevice;
import com.atmel.inscreenproximitydemo.device.MxtObject;
import com.atmel.inscreenproximitydemo.utility.FileHandler;
import com.atmel.inscreenproximitydemo.utility.Utility;

/**
 * project:InscreenProximityDemo <BR>
 * class name:T37DataHandler <BR>
 * 
 * @author david.dong create:2015年2月17日下午8:39:43
 */
public class T37Handler {

	private MaxtouchJni maxtouchJni = new MaxtouchJni();
	private Utility utility = new Utility();

	private int startPosition = -1;
	private int size = -1;
	private final int type = 37;
	
	StringBuffer sb = new StringBuffer();
	
	/**
	 * @author david.dong
	 * @return the sb
	 */
	public StringBuffer getSb() {
		return sb;
	}

	/**
	 * @author david.dong
	 * @param sb the sb to set
	 */
	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}

	/**
	 * constructor of class:T37Handler <BR>
	 * 
	 * @author david.dong create:2015年2月19日下午11:30:22
	 */
	public T37Handler(MxtDevice mxtDevice) {

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
				Log.v("T37 message", "T37 init fail, no object found in mxt device!");
			}
		}
		else{
			
			Log.v("T37 message", "T37 init fail!");
		}
	}

	
	public boolean readSelfReference(SelfReference selfReference){
		
		ConstantFactory.isReadSelfReferenceDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readSelfReference() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readSelfReference() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readSelfReference() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readSelfReference() fail, invalid matixY size!");
			return false;
		}
		
		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		//pageNumber = 14;
		
		t6Handler.t6SelfReference();
		
		ArrayList<Integer> tmpSelfReference = new ArrayList<Integer>();
		
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readSelfReference() fail, read incorrect page number!");
				//return false;
			}
			
			if(tmpRawData[1] == 0){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					
					/*
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					*/
					tmpIntRawData.add(delta16);
				}
				
				tmpSelfReference.addAll(tmpIntRawData.subList(0, 56));
				// add for speed up execution speed by reducing unnecessary read byte
				break;
			}
			
			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readSelfReference() fail, page up error!");
				//return false;
			}
			
		}
		
		int count =0;
		selfReference.getEvenXSelfReference().clear();
		selfReference.getOddXSelfReference().clear();
		selfReference.getySelfReference().clear();
		
		for(int i=0;i<tmpSelfReference.size();i++){
			
			if(i < matixYSize){
				
				YSelfReference ySelfReference = new YSelfReference();
				ySelfReference.setIndex(i);
				ySelfReference.setReference(tmpSelfReference.get(i));
				selfReference.getySelfReference().add(ySelfReference);
			}
			
			if((i > 19) && (i < 36)){
				
				XSelfReference xSelfReference = new XSelfReference();
				xSelfReference.setIndex(i-matixYSize);
				xSelfReference.setReference(tmpSelfReference.get(i));
				selfReference.getEvenXSelfReference().add(xSelfReference);
			}	
			
			if((i > 39) && (i < 56)){
				
				XSelfReference xSelfReference = new XSelfReference();
				xSelfReference.setIndex(i-40);
				xSelfReference.setReference(tmpSelfReference.get(i));
				selfReference.getOddXSelfReference().add(xSelfReference);
				
			}
					
		}
		
		ConstantFactory.isReadSelfReferenceDone = true;
		
		return true;
		
	}
	
	
	
	public boolean readAllSelfDelta(SelfDelta selfDelta,SingleEndDelta singleEndDelta,HoverDelta hoverDelta){
		
		ConstantFactory.isReadAllSelfDeltaDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readSelfDelta() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readSelfDelta() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readSelfDelta() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readSelfDelta() fail, invalid matixY size!");
			return false;
		}
		
		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		
		t6Handler.t6SelfDelta();
		
		ArrayList<Integer> tmpHoverDelta = new ArrayList<Integer>();
		ArrayList<Integer> tmpSelfDelta = new ArrayList<Integer>();
		ArrayList<Integer> tmpSEDelta = new ArrayList<Integer>();
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readSelfDelta() fail, read incorrect page number!");
				//return false;
			}
			
			if(tmpRawData[1] == 0){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					/* used for unsigned number, used for reference value
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					*/
					
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					
					/*
					int lsb = (int)(tmpRawData[j * 2] & 0xff);
					int msb = (int)(tmpRawData[j * 2 + 1] & 0xff);
					
					int delta16 = (short)(lsb | (msb << 8));
					*/
					// mutualDelta.getRawDelta().add(delta16);
					tmpIntRawData.add(delta16);
				}
				tmpSelfDelta.addAll(tmpIntRawData.subList(0, matixXSize+matixYSize));
				// add for speed up execution speed by reducing unnecessary read byte
				//break;
			}
			
			if(tmpRawData[1] == 2){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					/* used for unsigned number, used for reference value
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					*/
					
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					
					/*
					int lsb = (int)(tmpRawData[j * 2] & 0xff);
					int msb = (int)(tmpRawData[j * 2 + 1] & 0xff);
					
					int delta16 = (short)(lsb | (msb << 8));
					*/
					// mutualDelta.getRawDelta().add(delta16);
					tmpIntRawData.add(delta16);
				}
				tmpHoverDelta.addAll(tmpIntRawData.subList(0, matixXSize+matixYSize));
				// add for speed up execution speed by reducing unnecessary read byte
				//break;
			}

			
			if(tmpRawData[1] == 4){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					/* used for unsigned number, used for reference value
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					*/
					
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					
					
					/*
					int lsb = (int)(tmpRawData[j * 2] & 0xff);
					int msb = (int)(tmpRawData[j * 2 + 1] & 0xff);
					
					int delta16 = (short)(lsb | (msb << 8));
					*/
					
					// mutualDelta.getRawDelta().add(delta16);
					tmpIntRawData.add(delta16);
				}
				tmpSEDelta.addAll(tmpIntRawData.subList(0, matixXSize+matixYSize));
				// add for speed up execution speed by reducing unnecessary read byte
				break;
			}
			
			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readSelfDelta() fail, page up error!");
				//return false;
			}
			
		}
		
		selfDelta.getxSelfRawDelta().clear();
		selfDelta.getySelfRawDelta().clear();
		hoverDelta.getxHoverRawDelta().clear();
		hoverDelta.getyHoverRawDelta().clear();
		singleEndDelta.getRawXDelta().clear();
		singleEndDelta.getRawYDelta().clear();
		
		
		for(int i=0;i<tmpSelfDelta.size();i++){
			
			if(i < matixYSize){
				
				YSelfDelta ySelfDelta = new YSelfDelta();
				ySelfDelta.setIndex(i);
				ySelfDelta.setDelta(tmpSelfDelta.get(i));
				selfDelta.getySelfRawDelta().add(ySelfDelta);
			}
			else{
				
				XSelfDelta xSelfDelta = new XSelfDelta();
				xSelfDelta.setIndex(i-matixYSize);
				xSelfDelta.setDelta(tmpSelfDelta.get(i));
				selfDelta.getxSelfRawDelta().add(xSelfDelta);
			}
				
		}
		
		//singleEndDelta.getRawXDelta().clear();
		for(int i=0;i<tmpSEDelta.size();i++){
			
			if(i < matixYSize){
				
				YSingleEndDelta ySingleEndDelta = new YSingleEndDelta();
				ySingleEndDelta.setIndex(i);
				ySingleEndDelta.setDelta(tmpSEDelta.get(i));
				singleEndDelta.getRawYDelta().add(ySingleEndDelta);
				
			}
			else{
				
				XSingleEndDelta xSingleEndDelta = new XSingleEndDelta();
				xSingleEndDelta.setIndex(i-matixYSize);
				xSingleEndDelta.setDelta(tmpSEDelta.get(i));
				singleEndDelta.getRawXDelta().add(xSingleEndDelta);
			}
				
		}
		
		for(int i=0;i<tmpHoverDelta.size();i++){
			
			if(i < matixYSize){
				
				YHoverDelta yHoverDelta = new YHoverDelta();
				yHoverDelta.setIndex(i);
				yHoverDelta.setDelta(tmpHoverDelta.get(i));
				hoverDelta.getyHoverRawDelta().add(yHoverDelta);
				
			}
			else{
				
				XHoverDelta xHoverDelta = new XHoverDelta();
				xHoverDelta.setIndex(i-matixYSize);
				xHoverDelta.setDelta(tmpHoverDelta.get(i));
				hoverDelta.getxHoverRawDelta().add(xHoverDelta);
			}
				
		}
		
		ConstantFactory.isReadAllSelfDeltaDone = true;
		
		return true;
	}
	
	
	
	
	
	
	
	
	public boolean readSelfAndSeDelta(SelfDelta selfDelta,SingleEndDelta singleEndDelta){
		
		ConstantFactory.isReadSelfSeDeltaDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readSelfDelta() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readSelfDelta() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readSelfDelta() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readSelfDelta() fail, invalid matixY size!");
			return false;
		}
		
		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		
		t6Handler.t6SelfDelta();
		
		ArrayList<Integer> tmpSelfDelta = new ArrayList<Integer>();
		ArrayList<Integer> tmpSEDelta = new ArrayList<Integer>();
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readSelfDelta() fail, read incorrect page number!");
				//return false;
			}
			
			if(tmpRawData[1] == 0){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					/* used for unsigned number, used for reference value
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					*/
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					
					// mutualDelta.getRawDelta().add(delta16);
					tmpIntRawData.add(delta16);
				}
				tmpSelfDelta.addAll(tmpIntRawData.subList(0, matixXSize+matixYSize));
				// add for speed up execution speed by reducing unnecessary read byte
				//break;
			}
			
			if(tmpRawData[1] == 2){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					/* used for unsigned number, used for reference value
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					*/
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					
					// mutualDelta.getRawDelta().add(delta16);
					tmpIntRawData.add(delta16);
				}
				tmpSEDelta.addAll(tmpIntRawData.subList(20, 52));
				// add for speed up execution speed by reducing unnecessary read byte
				break;
			}

			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readSelfDelta() fail, page up error!");
				//return false;
			}
			
		}
		
		selfDelta.getxSelfRawDelta().clear();
		selfDelta.getySelfRawDelta().clear();
		for(int i=0;i<tmpSelfDelta.size();i++){
			
			if(i < matixYSize){
				
				YSelfDelta ySelfDelta = new YSelfDelta();
				ySelfDelta.setIndex(i);
				ySelfDelta.setDelta(tmpSelfDelta.get(i));
				selfDelta.getySelfRawDelta().add(ySelfDelta);
			}
			else{
				
				XSelfDelta xSelfDelta = new XSelfDelta();
				xSelfDelta.setIndex(i-matixYSize);
				xSelfDelta.setDelta(tmpSelfDelta.get(i));
				selfDelta.getxSelfRawDelta().add(xSelfDelta);
			}
				
		}
		
		singleEndDelta.getRawXDelta().clear();
		for(int i=0;i<tmpSEDelta.size();i++){
			
				XSingleEndDelta xSingleEndDelta = new XSingleEndDelta();
				xSingleEndDelta.setIndex(i);
				xSingleEndDelta.setDelta(tmpSEDelta.get(i));
				singleEndDelta.getRawXDelta().add(xSingleEndDelta);
		}
		
		
		ConstantFactory.isReadSelfSeDeltaDone = true;
		
		return true;
		
	}
	
	
	public boolean readSelfDelta(SelfDelta selfDelta){
		
		ConstantFactory.isReadSelfDeltaDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readSelfDelta() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readSelfDelta() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readSelfDelta() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readSelfDelta() fail, invalid matixY size!");
			return false;
		}
		
		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		
		t6Handler.t6SelfDelta();
		
		ArrayList<Integer> tmpSelfDelta = new ArrayList<Integer>();
		
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readSelfDelta() fail, read incorrect page number!");
				//return false;
			}
			
			if(tmpRawData[1] == 0){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					/* used for unsigned number, used for reference value
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					*/
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					
					// mutualDelta.getRawDelta().add(delta16);
					tmpIntRawData.add(delta16);
				}
				tmpSelfDelta.addAll(tmpIntRawData.subList(0, matixXSize+matixYSize));
				// add for speed up execution speed by reducing unnecessary read byte
				break;
			}
			
			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readSelfDelta() fail, page up error!");
				//return false;
			}
			
		}
		
		int count =0;
		selfDelta.getxSelfRawDelta().clear();
		selfDelta.getySelfRawDelta().clear();
		for(int i=0;i<tmpSelfDelta.size();i++){
			
			if(i < matixYSize){
				
				YSelfDelta ySelfDelta = new YSelfDelta();
				ySelfDelta.setIndex(i);
				ySelfDelta.setDelta(tmpSelfDelta.get(i));
				selfDelta.getySelfRawDelta().add(ySelfDelta);
			}
			else{
				
				XSelfDelta xSelfDelta = new XSelfDelta();
				xSelfDelta.setIndex(i-matixYSize);
				xSelfDelta.setDelta(tmpSelfDelta.get(i));
				selfDelta.getxSelfRawDelta().add(xSelfDelta);
			}
				
		}
		
		ConstantFactory.isReadSelfDeltaDone = true;
		
		return true;
	}
	
	
	
	public boolean readSEReference(SingleEndReference singleEndReferece){
		
		ConstantFactory.isReadSEReferenceDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readSEReference() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readSEReference() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readSEReference() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readSEReference() fail, invalid matixY size!");
			return false;
		}
		
		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		
		t6Handler.t6SelfReference();
		
		ArrayList<Integer> tmpSEReference = new ArrayList<Integer>();
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readSEReference() fail, read incorrect page number!");
				//return false;
			}
			
			if(tmpRawData[1] == 2){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					
					/*
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					*/
					tmpIntRawData.add(delta16);
				}
				tmpSEReference.addAll(tmpIntRawData.subList(20, 56));
				// add for speed up execution speed by reducing unnecessary read byte
				break;
			}
			
			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readSEReference() fail, page up error!");
				//return false;
			}
			
		}
		
		int count =0;
		singleEndReferece.getEvenXSEReference().clear();
		singleEndReferece.getOddXSEReference().clear();
		
		for(int i=0;i<tmpSEReference.size();i++){
			
			if(i < 16){
				
				XSingleEndReference xSingleEndReference = new XSingleEndReference();
				xSingleEndReference.setIndex(i);
				xSingleEndReference.setDelta(tmpSEReference.get(i));
				singleEndReferece.getEvenXSEReference().add(xSingleEndReference);
			}
				
			if(i > 19){
				
				XSingleEndReference xSingleEndReference = new XSingleEndReference();
				xSingleEndReference.setIndex(i-20);
				xSingleEndReference.setDelta(tmpSEReference.get(i));
				singleEndReferece.getOddXSEReference().add(xSingleEndReference);
				
			}
		}
		
		ConstantFactory.isReadSEReferenceDone = true;
		
		return true;
	}
	
	
	
	public boolean readSEDelta(SingleEndDelta singleEndDelta){
		
		ConstantFactory.isReadSEDeltaDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readSEDelta() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readSEDelta() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readSEDelta() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readSEDelta() fail, invalid matixY size!");
			return false;
		}
		
		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		
		t6Handler.t6SelfDelta();
		
		ArrayList<Integer> tmpSEDelta = new ArrayList<Integer>();
		
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readSEDelta() fail, read incorrect page number!");
				//return false;
			}
			
			if(tmpRawData[1] == 4){
				
				for (int j = 1; j < tmpRawData.length / 2; j++) {
					
					/* used for unsigned number, used for reference value
					int lsb = tmpRawData[j * 2] & 0xff;
					int msb = tmpRawData[j * 2 + 1] & 0xff;
					int delta16 = lsb |( msb << 8);
					*/
					int delta16 = ((int) (tmpRawData[j * 2]))
							| ((int) (tmpRawData[j * 2 + 1]) << 8);
					
					// mutualDelta.getRawDelta().add(delta16);
					tmpIntRawData.add(delta16);
				}
				tmpSEDelta.addAll(tmpIntRawData.subList(0, 22));
				// add for speed up execution speed by reducing unnecessary read byte
				break;
			}
			
			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readSEDelta() fail, page up error!");
				//return false;
			}
			
		}
		
		singleEndDelta.getRawYDelta().clear();
		for(int i=0;i<tmpSEDelta.size();i++){
			
				//XSingleEndDelta xSingleEndDelta = new XSingleEndDelta();
				//xSingleEndDelta.setIndex(i);
				//xSingleEndDelta.setDelta(tmpSEDelta.get(i));
				//singleEndDelta.getRawXDelta().add(xSingleEndDelta);
			
			YSingleEndDelta ySingleEndDelta = new YSingleEndDelta();
			ySingleEndDelta.setIndex(i);
			ySingleEndDelta.setDelta(tmpSEDelta.get(i));
			singleEndDelta.getRawYDelta().add(ySingleEndDelta);
			
		}
		
		ConstantFactory.isReadSEDeltaDone = true;
		
		return true;
	}
	
	
	public boolean readMutualReference(MutualReference mutualReference){
		
		ConstantFactory.isReadMutualReferenceDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readMutualReference() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readMutualReference() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readMutualReference() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readMutualReference() fail, invalid matixY size!");
			return false;
		}

		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		//pageNumber = 14;
		
		t6Handler.t6MutualReference();

		ArrayList<Integer> tmpMutualReference = new ArrayList<Integer>();
		
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readMutualDelta() fail, read incorrect page number!");
				//return false;
			}
			
			for (int j = 1; j < tmpRawData.length / 2; j++) {
				
				int lsb = tmpRawData[j * 2] & 0xff;
				int msb = tmpRawData[j * 2 + 1] & 0xff;
				
				int delta16 = lsb |( msb << 8);

				/* used for signed byte, used for delta
				int delta16 = ((int) (tmpRawData[j * 2]))
						| ((int) (tmpRawData[j * 2 + 1]) << 8);
				*/
				tmpIntRawData.add(delta16);
			}
			
			tmpMutualReference.addAll(tmpIntRawData);
			
			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readMutualDelta() fail, page up error!");
				//return false;
			}
		}
	
		int count =0;
		mutualReference.getMutualReference().clear();
		for(int i=0;i<matixXSize;i++){
			
			for(int j=0;j<matixYSize;j++){
				
				NodeMutualReference nodeMutualReference = new NodeMutualReference();
				nodeMutualReference.setIndex(new Point(i,j));
				nodeMutualReference.setDelta(tmpMutualReference.get(count));
				mutualReference.getMutualReference().add(nodeMutualReference);
				count++;
			}
		}
		
		/*
	    sb.delete(0, sb.length());
		String size = String.valueOf(tmpMutualDelta.size()) + "\n";
		
		sb.append(size);
		sb.append(" \n");
		
		for(int i=0;i<tmpMutualDelta.size();i++){
			
			sb.append(String.valueOf(tmpMutualDelta.get(i)));
			if((i+1)%20==0){
				sb.append("              \n");
			}
			else{
				sb.append(" ");
			}
			
		}
		*/
		
		ConstantFactory.isReadMutualReferenceDone = true;
		
		return true;
		
	}
	
	public boolean readMutualDelta(MutualDelta mutualDelta) {

		ConstantFactory.isReadMutualDeltaDone = false;
		
		byte[] tmpRawData = new byte[size+1];
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		T100Handler t100Handler = new T100Handler(ConstantFactory.mxtDevice);
		
		
		int xSize = t100Handler.getXSize();
		if(xSize == -1){
			Log.v("T37 message", "readMutualDelta() fail, invalid X size!");
			return false;
		}
		
		int ySize = t100Handler.getYSize();
		if(ySize == -1){
			Log.v("T37 message", "readMutualDelta() fail, invalid Y size!");
			return false;
		}
		
		
		int matixXSize = utility.findMatixXOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixXSize == -1){
			
			Log.v("T37 message", "readMutualDelta() fail, invalid matixX size!");
			return false;
		}
		
		int matixYSize = utility.findMatixYOnInfoBlock(ConstantFactory.mxtDevice);
		if(matixYSize == -1){
			
			Log.v("T37 message", "readMutualDelta() fail, invalid matixY size!");
			return false;
		}

		int nodesPerPage = (size+1-2)/ConstantFactory.bytesPerNode;
		double pageNumber = (double)(matixXSize * matixYSize)/nodesPerPage;
		
		int ceilPageNumber = (int) Math.ceil(pageNumber);
		
		t6Handler.t6MutualDelta();

		ArrayList<Integer> tmpMutualDelta = new ArrayList<Integer>();
		
		
		for(int i=0; i< ceilPageNumber;i++){
			
			ArrayList<Integer> tmpIntRawData = new ArrayList<Integer>();
			tmpRawData = maxtouchJni.ReadRegister(startPosition, size+1);
			if(tmpRawData[1] != i){
				
				Log.v("T37message", "readMutualDelta() fail, read incorrect page number!");
				//return false;
			}
			
			
			
			
			for (int j = 1; j < tmpRawData.length / 2; j++) {
				
				//int lsb = tmpRawData[j * 2] & 0xff;
				//int msb = tmpRawData[j * 2 + 1] & 0xff;
				
				//int delta16 = lsb |( msb << 8);

				int delta16 = ((int) (tmpRawData[j * 2]))
						| ((int) (tmpRawData[j * 2 + 1]) << 8);
				
				// mutualDelta.getRawDelta().add(delta16);
				tmpIntRawData.add(delta16);
			}
			tmpMutualDelta.addAll(tmpIntRawData);
			if(!t6Handler.t6PageUp()){
				Log.v("T37message", "readMutualDelta() fail, page up error!");
				//return false;
			}
			
		}
	
		
		int count =0;
		mutualDelta.getRawDelta().clear();
		for(int i=0;i<matixXSize;i++){
			
			for(int j=0;j<matixYSize;j++){
				
				NodeMutualDelta nodeMutualDelta = new NodeMutualDelta();
				nodeMutualDelta.setIndex(new Point(i,j));
				nodeMutualDelta.setDelta(tmpMutualDelta.get(count));
				mutualDelta.getRawDelta().add(nodeMutualDelta);
				count++;
			}
		}
		
		/*
	    sb.delete(0, sb.length());
		String size = String.valueOf(tmpMutualDelta.size()) + "\n";
		
		sb.append(size);
		sb.append(" \n");
		
		for(int i=0;i<tmpMutualDelta.size();i++){
			
			sb.append(String.valueOf(tmpMutualDelta.get(i)));
			if((i+1)%20==0){
				sb.append("              \n");
			}
			else{
				sb.append(" ");
			}
			
		}
		*/
		
		ConstantFactory.isReadMutualDeltaDone = true;
		
		return true;
	}
}
