package com.atmel.inscreenproximitydemo.device;
/**
 * project:InscreenProximityDemo <BR>
 * class name:MxtIdInfo <BR>
 * @author david.dong
 * create:2015年4月10日上午10:31:34
 */
public class MxtIdInfo {

	private int familyId;
	private int variantId;
	private int version;
	private int build;
	private int matrixXSize;
	private int matrixYSize;
	private int numberObjects;
	
	
	
	
	
	
	public MxtIdInfo() {
		this.familyId = 255;
		this.variantId = 255;
		this.version = 255;
		this.build = 255;
		this.matrixXSize = 255;
		this.matrixYSize = 255;
		this.numberObjects = 255;
	}
	public int getFamilyId() {
		return familyId;
	}
	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	public int getVariantId() {
		return variantId;
	}
	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getBuild() {
		return build;
	}
	public void setBuild(int build) {
		this.build = build;
	}
	public int getMatrixXSize() {
		return matrixXSize;
	}
	public void setMatrixXSize(int matrixXSize) {
		this.matrixXSize = matrixXSize;
	}
	public int getMatrixYSize() {
		return matrixYSize;
	}
	public void setMatrixYSize(int matrixYSize) {
		this.matrixYSize = matrixYSize;
	}
	public int getNumberObjects() {
		return numberObjects;
	}
	public void setNumberObjects(int numberObjects) {
		this.numberObjects = numberObjects;
	}
	
	
	
	
	
}
