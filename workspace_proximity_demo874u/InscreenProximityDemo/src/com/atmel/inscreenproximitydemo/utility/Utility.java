/**
 * project:InscreenProximityDemo <BR>
 * file name:Utility.java <BR>
 * @author david.dong
 * create:2015年2月19日下午11:02:19
 * 
 */
package com.atmel.inscreenproximitydemo.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.atmel.inscreenproximitydemo.datatype.HoverDelta;
import com.atmel.inscreenproximitydemo.datatype.MutualDelta;
import com.atmel.inscreenproximitydemo.datatype.NodeMutualDelta;
import com.atmel.inscreenproximitydemo.datatype.SelfDelta;
import com.atmel.inscreenproximitydemo.datatype.SingleEndDelta;
import com.atmel.inscreenproximitydemo.datatype.XHoverDelta;
import com.atmel.inscreenproximitydemo.datatype.XSelfDelta;
import com.atmel.inscreenproximitydemo.datatype.XSingleEndDelta;
import com.atmel.inscreenproximitydemo.datatype.YHoverDelta;
import com.atmel.inscreenproximitydemo.datatype.YSingleEndDelta;
import com.atmel.inscreenproximitydemo.device.MxtDevice;
import com.atmel.inscreenproximitydemo.device.MxtInfo;
import com.atmel.inscreenproximitydemo.device.MxtObject;

/**
 * project:InscreenProximityDemo <BR>
 * class name:Utility <BR>
 * @author david.dong
 * create:2015年2月19日下午11:02:19
 */
public class Utility {

	/**
	 * return to the index of type object in object table
	 * 根据object类型返回object table中的索引
	 * @author david.dong
	 * create:2015年2月19日下午11:02:19
	 */
	public int findIndexOnObjectsByType(MxtDevice mxtDevice,int type){
	
		int rtn = -1;
		
		ArrayList<MxtObject> objects = mxtDevice.getMxtInfo().getMxtObjects();
		
		for(int i=0;i<objects.size();i++){
			
			if(objects.get(i).getType()==type){
				
				rtn = i;
				return rtn;
			}	
		}
		
		return rtn;
	}
	
	/**
	 * return to the matrix X 
	 * @author david.dong
	 * create:2015年2月19日下午11:02:19
	 */
	public int findMatixXOnInfoBlock(MxtDevice mxtDevice){
		
		int rtn = -1;
		
		MxtInfo mxtInfo = mxtDevice.getMxtInfo();
		
		rtn = mxtInfo.getMxtIdInfo().getMatrixXSize();
		
		return rtn;
	}
	/**
	 * return to the matrix Y 
	 * @author david.dong
	 * create:2015年2月19日下午11:02:19
	 */
	public int findMatixYOnInfoBlock(MxtDevice mxtDevice){
		
		int rtn = -1;
		
		MxtInfo mxtInfo = mxtDevice.getMxtInfo();
		
		rtn = mxtInfo.getMxtIdInfo().getMatrixYSize();
		
		return rtn;
	}
	/**
	 * return to the sum of absolute value of all single-end delta 
	 * @author david.dong
	 * create:2015年2月19日下午11:02:19
	 */
	public int findAbsSumOnSEXDelta(SingleEndDelta seDelta){
		
		int rtn = -1;
		
		ArrayList<XSingleEndDelta> seDeltas = seDelta.getRawXDelta();
		
		int sum = 0;
		
		for(int i=0;i<seDeltas.size();i++){
			
			int rawDelta = seDeltas.get(i).getDelta();
			sum += Math.abs(rawDelta);
		}
		
		rtn = sum;
		return rtn;
		
	}
	
	public int findAbsSumOnSCTXDelta(SelfDelta selfDelta, int start,int end){
		
		int rtn = -1;
		
		ArrayList<XSelfDelta> xseDeltas = selfDelta.getxSelfRawDelta();
		
		int sum = 0;
		
		for(int i=start;i<end;i++){
			
			int rawDelta = xseDeltas.get(i).getDelta();
			sum += Math.abs(rawDelta);
		}
		
		rtn = sum;
		return rtn;
		
	}
	
	
	public int findAbsSumOnSEXDelta(SingleEndDelta seDelta,int start,int end){
		
		int rtn = -1;
		if((start <0)||(end>seDelta.getRawXDelta().size())){
			
		}else{
			
			List<XSingleEndDelta> seDeltas = seDelta.getRawXDelta().subList(start, end);
			
			int sum = 0;
			
			for(int i=0;i<seDeltas.size();i++){
				
				int rawDelta = seDeltas.get(i).getDelta();
				sum += Math.abs(rawDelta);
			}
			
			rtn = sum;
			
		}
		return rtn;
	}
	
	
	public int findAbsSumOnSEYDelta(SingleEndDelta seDelta){
		
		int rtn = -1;
		
		ArrayList<YSingleEndDelta> seYDeltas = seDelta.getRawYDelta();
		
		int sum = 0;
		
		for(int i=0;i<seYDeltas.size();i++){
			
			int rawDelta = seYDeltas.get(i).getDelta();
			sum += Math.abs(rawDelta);
		}
		
		rtn = sum;
		
		return rtn;
	}
	
	public int findAbsSumOnSEYDelta(SingleEndDelta seDelta,int start,int end){
		
		int rtn = -1;
		if((start <0)||(end>seDelta.getRawYDelta().size())){
			
		}else{
			
			
			List<YSingleEndDelta> seYDeltas = seDelta.getRawYDelta().subList(start, end);
			
			int sum = 0;
			
			for(int i=0;i<seYDeltas.size();i++){
				
				int rawDelta = seYDeltas.get(i).getDelta();
				sum += Math.abs(rawDelta);
			}
			
			rtn = sum;
		}
		return rtn;
	}
	
	
	
	public int findSumOnHoverXDelta(HoverDelta hoverDelta){
		
		int rtn = -1;
		
		ArrayList<XHoverDelta> xHoverDeltas = hoverDelta.getxHoverRawDelta();
		
		int sum = 0;
		
		for(int i=0;i<xHoverDeltas.size();i++){
			
			int rawDelta = xHoverDeltas.get(i).getDelta();
			sum += rawDelta;;
		}
		
		rtn = sum;
		return rtn;
	}
	
	
	public int findSumOnHoverXDelta(HoverDelta hoverDelta,int start,int end){
		
		int rtn = -1;
		if((start <0)||(end>hoverDelta.getxHoverRawDelta().size())){
			
		}else{
			
			List<XHoverDelta> xHoverDeltas =  hoverDelta.getxHoverRawDelta().subList(start, end);
			
			int sum = 0;
			
			for(int i=0;i<xHoverDeltas.size();i++){
				
				int rawDelta = xHoverDeltas.get(i).getDelta();
				sum += rawDelta;;
			}
			
			rtn = sum;
		}
		return rtn;
	}
	
	
	public int findAbsSumOnHoverXDelta(HoverDelta hoverDelta){
		
		int rtn = -1;
		
		ArrayList<XHoverDelta> xHoverDeltas = hoverDelta.getxHoverRawDelta();
		
		int sum = 0;
		
		for(int i=0;i<xHoverDeltas.size();i++){
			
			int rawDelta = xHoverDeltas.get(i).getDelta();
			sum += Math.abs(rawDelta);
		}
		
		rtn = sum;
		return rtn;
		
		
	}
	
	public int findAbsSumOnHoverXDelta(HoverDelta hoverDelta,int start,int end){
		
		int rtn = -1;
		if((start <0)||(end>hoverDelta.getxHoverRawDelta().size())){
			
		}else{
			
			List<XHoverDelta> xHoverDeltas = hoverDelta.getxHoverRawDelta().subList(start, end);
			
			int sum = 0;
			
			for(int i=0;i<xHoverDeltas.size();i++){
				
				int rawDelta = xHoverDeltas.get(i).getDelta();
				sum += Math.abs(rawDelta);
			}
			
			rtn = sum;
		}
		
		return rtn;
	}
	
	public int findSumOnHoverYDelta(HoverDelta hoverDelta){
		
		int rtn = -1;
		
		ArrayList<YHoverDelta> yHoverDeltas = hoverDelta.getyHoverRawDelta();
		
		int sum = 0;
		
		for(int i=0;i<yHoverDeltas.size();i++){
			
			int rawDelta = yHoverDeltas.get(i).getDelta();
			sum += rawDelta;;
		}
		
		rtn = sum;
		return rtn;
	}
	
	
	public int findSumOnHoverYDelta(HoverDelta hoverDelta,int start,int end){
		
		int rtn = -1;
		
		if((start <0)||(end>hoverDelta.getyHoverRawDelta().size())){
			
		}else{
			
			List<YHoverDelta> yHoverDeltas =  hoverDelta.getyHoverRawDelta().subList(start, end);
			
			int sum = 0;
			
			for(int i=0;i<yHoverDeltas.size();i++){
				
				int rawDelta = yHoverDeltas.get(i).getDelta();
				sum += rawDelta;;
			}
			
			rtn = sum;
		}
		
		return rtn;
	}
	
	
	public int findAbsSumOnHoverYDelta(HoverDelta hoverDelta){
		
		int rtn = -1;
		
		ArrayList<YHoverDelta> yHoverDeltas = hoverDelta.getyHoverRawDelta();
		
		int sum = 0;
		
		for(int i=0;i<yHoverDeltas.size();i++){
			
			int rawDelta = yHoverDeltas.get(i).getDelta();
			sum += Math.abs(rawDelta);
		}
		
		rtn = sum;
		return rtn;
	}
	
	public int findAbsSumOnHoverYDelta(HoverDelta hoverDelta, int start, int end){
		
		int rtn = -1;
		
		if((start <0)||(end>hoverDelta.getyHoverRawDelta().size())){
			
		}
		else{
			List<YHoverDelta> yHoverDeltas = hoverDelta.getyHoverRawDelta().subList(start, end);
			int sum = 0;
			
			for(int i=0;i<yHoverDeltas.size();i++){
				
				int rawDelta = yHoverDeltas.get(i).getDelta();
				sum += Math.abs(rawDelta);
			}
			
			rtn = sum;
		}
		
		
		return rtn;
		
	}
	
	
	public int findSumOnSEXDelta(SingleEndDelta seDelta){
		
		int rtn = -1;
		
		ArrayList<XSingleEndDelta> seDeltas = seDelta.getRawXDelta();
		
		int sum = 0;
		
		for(int i=0;i<seDeltas.size();i++){
			
			int rawDelta = seDeltas.get(i).getDelta();
			sum += rawDelta;;
		}
		
		rtn = sum;
		return rtn;
	}
	
	public int findSumOnSEXDelta(SingleEndDelta seDelta,int start,int end){
		
		int rtn = -1;
		
		if((start <0)||(end>seDelta.getRawXDelta().size())){
			
		}else{
			
			List<XSingleEndDelta> seDeltas =  seDelta.getRawXDelta().subList(start, end);
			
			int sum = 0;
			
			for(int i=0;i<seDeltas.size();i++){
				
				int rawDelta = seDeltas.get(i).getDelta();
				sum += rawDelta;;
			}
			
			rtn = sum;
		}
		
		return rtn;
	}
	
	public int findSumOnSEYDelta(SingleEndDelta seDelta){
		
		int rtn = -1;
		
		ArrayList<YSingleEndDelta> seYDeltas = seDelta.getRawYDelta();
		
		int sum = 0;
		
		for(int i=0;i<seYDeltas.size();i++){
			
			int rawDelta = seYDeltas.get(i).getDelta();
			sum += rawDelta;;
		}
		
		rtn = sum;
		return rtn;
	}
	
	public int findSumOnSEYDelta(SingleEndDelta seDelta,int start,int end){
		
		int rtn = -1;
		
		if((start <0)||(end>seDelta.getRawYDelta().size())){
			
		}else{
			
			List<YSingleEndDelta> seYDeltas = seDelta.getRawYDelta().subList(start, end);
			
			int sum = 0;
			
			for(int i=0;i<seYDeltas.size();i++){
				
				int rawDelta = seYDeltas.get(i).getDelta();
				sum += rawDelta;;
			}
			
			rtn = sum;
		}
		
		return rtn;
	}
	
	/**
	 * return to the sub list of delta value over threshold 
	 * @author david.dong
	 * create:2015年2月19日下午11:02:19
	 */
	public ArrayList<NodeMutualDelta> findNodeOverThresholdOnMUDelta(MutualDelta mutualDelta, int threshold){
		
		ArrayList<NodeMutualDelta> rtnLists = new ArrayList<NodeMutualDelta>();
		
		ArrayList<NodeMutualDelta> rawDelta = mutualDelta.getRawDelta();
		
		for(int i=0;i<rawDelta.size();i++){
			
			NodeMutualDelta nodeDelta = rawDelta.get(i);
			int delta = nodeDelta.getDelta();
			
			if(delta > threshold){
				
				rtnLists.add(nodeDelta);
			}
		}
		
		return rtnLists;
	}
	
	/**
	 * return to the sum of absolute value of all single-end delta 
	 * @author david.dong
	 * create:2015年2月19日下午11:02:19
	 */
	public ArrayList<Integer> findIndexOverThresholdOnSeXDelta(SingleEndDelta seDelta, int threshold){
		
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		
		for(int i=0;i<seDelta.getRawXDelta().size();i++){
			
			XSingleEndDelta xSingleEndDelta = seDelta.getRawXDelta().get(i);
			int delta = xSingleEndDelta.getDelta();
			int index = xSingleEndDelta.getIndex();
			if(delta>threshold){
				
				rtn.add(index);
			}
		}
		
		return rtn;
	}

	public ArrayList<Integer> findIndexOverThresholdOnSeYDelta(SingleEndDelta seDelta, int threshold){
		
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		
		for(int i=0;i<seDelta.getRawYDelta().size();i++){
			
			YSingleEndDelta ySingleEndDelta = seDelta.getRawYDelta().get(i);
			int delta = ySingleEndDelta.getDelta();
			int index = ySingleEndDelta.getIndex();
			if(delta>threshold){
				
				rtn.add(index);
			}
		}
		
		return rtn;
	}
	
	public ArrayList<Integer> findIndexOverThresholdOnHoverXDelta(HoverDelta hoverDelta,int threshold){
		
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		
		ArrayList<XHoverDelta> xHoverRawDeltas = hoverDelta.getxHoverRawDelta();
		
		for(int i=0;i<xHoverRawDeltas.size();i++){
			
			XHoverDelta xHoverDelta = xHoverRawDeltas.get(i);
			int delta = xHoverDelta.getDelta();
			int index = xHoverDelta.getIndex();
			if(delta>threshold){
				rtn.add(index);
			}
		}
		
		return rtn;
	}
	
	
	public ArrayList<Integer> findIndexOverThresholdOnHoverYDelta(HoverDelta hoverDelta,int threshold){
		
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		
		ArrayList<YHoverDelta> yHoverRawDeltas = hoverDelta.getyHoverRawDelta();
		
		for(int i=0;i<yHoverRawDeltas.size();i++){
			
			YHoverDelta yHoverDelta = yHoverRawDeltas.get(i);
			int delta = yHoverDelta.getDelta();
			int index = yHoverDelta.getIndex();
			if(delta>threshold){
				rtn.add(index);
			}
		}
		
		return rtn;
	}
	
	
	public int findMaxOnHoverX(HoverDelta hoverDelta){
		
		int rtn = -1;
		
		ArrayList<XHoverDelta> xHoverDelta = hoverDelta.getxHoverRawDelta();
		ArrayList<Integer> tmpDelta = new ArrayList<Integer>();
		
		for(int i=0; i<xHoverDelta.size();i++){
			
			
			
			tmpDelta.add(xHoverDelta.get(i).getDelta());
		}
		
		rtn = Collections.max(tmpDelta);
		
		//rtn = tmpList.get(0);
		
		return rtn;
		
	}
	
	public int findMaxOnHoverY(HoverDelta hoverDelta){
		
		int rtn = -1;
		
		ArrayList<YHoverDelta> yHoverDelta = hoverDelta.getyHoverRawDelta();
		ArrayList<Integer> tmpDelta = new ArrayList<Integer>();
		
		for(int i=0; i<yHoverDelta.size();i++){
			
			
			
			tmpDelta.add(yHoverDelta.get(i).getDelta());
		}
		
		rtn = Collections.max(tmpDelta);
		
		//rtn = tmpList.get(0);
		
		return rtn;
		
	}
	
	public int findMaxOnAbsArrayList(ArrayList<XSingleEndDelta> xSingleEndDeltas){
		
		int rtn = -1;
		
		ArrayList<Integer> tmpList = new ArrayList<Integer>();
		
		for(int i=0; i<xSingleEndDeltas.size();i++){
			
			int delta = xSingleEndDeltas.get(i).getDelta();
			
			tmpList.add(Math.abs(delta));
		}
		
		rtn = Collections.max(tmpList);
		
		//rtn = tmpList.get(0);
		
		return rtn;
	}
	
	
	public int findMaxOnArrayList(ArrayList<XSingleEndDelta> xSingleEndDeltas){
		
		int rtn = -1;
		
		ArrayList<Integer> tmpList = new ArrayList<Integer>();
		
		for(int i=0; i<xSingleEndDeltas.size();i++){
			
			int delta = xSingleEndDeltas.get(i).getDelta();
			
			tmpList.add(delta);
		}
		
		rtn = Collections.max(tmpList);
		
		//rtn = tmpList.get(0);
		
		return rtn;
	}

	public ArrayList<XSingleEndDelta> filterFromAbsArray(SingleEndDelta seDelta, int threshold){
		
		ArrayList<XSingleEndDelta> rtnArray = new ArrayList<XSingleEndDelta>();
		ArrayList<XSingleEndDelta> xSingleEndDelta = seDelta.getRawXDelta();
		
		for(int i=0;i<xSingleEndDelta.size();i++){
			
			int index = xSingleEndDelta.get(i).getIndex();
			int delta = xSingleEndDelta.get(i).getDelta();
			
			int absDelta = Math.abs(delta);
			
			XSingleEndDelta tmpXSingleEndDelta = new XSingleEndDelta();
			tmpXSingleEndDelta.setIndex(index);
		
			if(absDelta > threshold){
				
				tmpXSingleEndDelta.setDelta(absDelta);
				
			}
			else{
				
				tmpXSingleEndDelta.setIndex(0);
			}
			
			rtnArray.add(tmpXSingleEndDelta);
		}
		
		return rtnArray;
	}
	
	
	public int findAbsSumArrayList(ArrayList<XSingleEndDelta> xSingleEndDeltas){
		
		int rtn = -1;
		
		int sum = 0;
		
		for(int i=0;i<xSingleEndDeltas.size();i++){
			
			int rawDelta = xSingleEndDeltas.get(i).getDelta();
			sum += Math.abs(rawDelta);
		}
		
		rtn = sum;
		return rtn;
	}
}