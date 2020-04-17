//------------------------------------------------------------------------------
/// \file   jniinterface.c
/// \brief  JNI functions for libmaxtouch
/// \author Nick Dyer
//------------------------------------------------------------------------------
// Copyright 2011 Atmel Corporation. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
//    1. Redistributions of source code must retain the above copyright notice,
//    this list of conditions and the following disclaimer.
//
//    2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY ATMEL ''AS IS'' AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
// EVENT SHALL ATMEL OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
// INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
// OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
// LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
// NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
// EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//------------------------------------------------------------------------------

#include <string.h>
#include <stdint.h>
#include <stdbool.h>

#include "com_atmel_inscreenproximitydemo_MaxtouchJni.h"
//#include "jni.h"
#include <jni.h>
#include "libmaxtouch/libmaxtouch.h"
#include <android/log.h>



struct libmaxtouch_ctx *ctx;
struct mxt_conn_info *conn;
struct mxt_device *mxt;

//******************************************************************************
/// \brief  JNI initialisation function
jint JNI_OnLoad(JavaVM *vm, void *reserved) {
	JNIEnv *env;

	if ((*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_4))
		return JNI_ERR;

	return JNI_VERSION_1_4;
}

//******************************************************************************
/// \brief  Scan for device
/// \return device found true/false
JNIEXPORT jboolean JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_Scan(
		JNIEnv * env, jobject this) {
	int ret;

	ret = mxt_new(&ctx);
	if (ret)
		return JNI_FALSE;

	// Enable logging
	//disable by david
	// mxt_set_log_fn(ctx, mxt_log_android);

	/* disabled by david
	 mxt_set_log_level(ctx, 4);
	 */
	//mxt_info(ctx, "libmaxtouch %s", MXT_VERSION);
	ret = mxt_scan(ctx, &conn, false);
	if (ret) {
		return JNI_FALSE;
	}

	ret = mxt_new_device(ctx, conn, &mxt);
	if (ret)
		return JNI_FALSE;

	return JNI_TRUE;
}

JNIEXPORT jboolean JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetInfo(
		JNIEnv * env, jobject this) {

	int ret;

	ret = mxt_get_info(mxt);

	return (ret == MXT_SUCCESS) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetInfoDebug
  (JNIEnv * env, jobject this){


	int ret;

	ret = mxt_get_info(mxt);

	return ret;
}

JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetSysfsPath
  (JNIEnv * env, jobject this){

	jstring path;
	char * pathNative = mxt->sysfs.mem_access_path;
	path = (*env)->NewStringUTF(env, pathNative);
	return path;
}


JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetSysfsDirectory
  (JNIEnv * env, jobject this){

	jclass stringClass;
	  jstring sysfsLocation;

	  char *szLocation;

	  szLocation = (char *)sysfs_get_directory(mxt);

	  stringClass = NULL;
	  stringClass = (*env)->FindClass(env, "java/lang/String");
	  sysfsLocation = (*env)->NewStringUTF(env, szLocation);

	  return sysfsLocation;
}

JNIEXPORT jbyteArray JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_ReadRegister
  (JNIEnv * env, jobject this, jint start_register, jint count){

	int ret;
	  unsigned char* buf;
	  jbyteArray jb = 0;

	  buf = (unsigned char *)calloc(count, sizeof(unsigned char));

	  if (buf == NULL)
	    return NULL;

	  ret = mxt_read_register(mxt, buf, start_register, count);

	  if (ret == MXT_SUCCESS)
	  {
	    jb=(*env)->NewByteArray(env, count);
	    (*env)->SetByteArrayRegion(env, jb, 0, count, buf);
	  }

	  free(buf);
	  return jb;
}

JNIEXPORT jint JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_WriteRegister
  (JNIEnv * env, jobject this, jint start_register, jbyteArray data){

	int ret;
	  int count;
	  unsigned char *buf;

	  /* transfer contents of byte array into buffer */
	  count=(*env)->GetArrayLength(env, data);
	  buf = (unsigned char *)calloc(count, sizeof(unsigned char));

	  if (buf == NULL)
	    return -1;

	  (*env)->GetByteArrayRegion(env, data, 0, count, buf);

	  ret = mxt_write_register(mxt, buf, start_register, count);

	  free(buf);
	  return ret;

}





JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_loadMxtDevice
  (JNIEnv * env, jobject this, jobject mxtDevice){

	jstring rst;
	int family = mxt->info.id->family;
	int variant = mxt->info.id->variant;
	int version = mxt->info.id->version;
	int build = mxt->info.id->build;
	int matrix_x_size = mxt->info.id->matrix_x_size;
	int matrix_y_size = mxt->info.id->matrix_y_size;
	int num_objects = mxt->info.id->num_objects;

    //set mxtIdInfo
	jclass mxtIdInfoClass = (*env)->FindClass(env, "com/atmel/inscreenproximitydemo/device/MxtIdInfo");
	jmethodID consMxtIdInfoClass =  (*env)->GetMethodID(env,mxtIdInfoClass, "<init>","()V");
	jobject mxtIdInfoObj =  (*env)->NewObject(env,mxtIdInfoClass,consMxtIdInfoClass);

	jmethodID setFamilyId = (*env)->GetMethodID(env,mxtIdInfoClass, "setFamilyId","(I)V");
	(*env)->CallVoidMethod(env,mxtIdInfoObj,setFamilyId,family);

	jmethodID setVariantId = (*env)->GetMethodID(env,mxtIdInfoClass, "setVariantId","(I)V");
	(*env)->CallVoidMethod(env,mxtIdInfoObj,setVariantId,variant);

	jmethodID setVersion = (*env)->GetMethodID(env,mxtIdInfoClass, "setVersion","(I)V");
	(*env)->CallVoidMethod(env,mxtIdInfoObj,setVersion,version);

	jmethodID setBuild = (*env)->GetMethodID(env,mxtIdInfoClass, "setBuild","(I)V");
	(*env)->CallVoidMethod(env,mxtIdInfoObj,setBuild,build);

	jmethodID setMatrixXSize = (*env)->GetMethodID(env,mxtIdInfoClass, "setMatrixXSize","(I)V");
	(*env)->CallVoidMethod(env,mxtIdInfoObj,setMatrixXSize,matrix_x_size);

	jmethodID setMatrixYSize = (*env)->GetMethodID(env,mxtIdInfoClass, "setMatrixYSize","(I)V");
	(*env)->CallVoidMethod(env,mxtIdInfoObj,setMatrixYSize,matrix_y_size);

	jmethodID setNumberObjects = (*env)->GetMethodID(env,mxtIdInfoClass, "setNumberObjects","(I)V");
	(*env)->CallVoidMethod(env,mxtIdInfoObj,setNumberObjects,num_objects);




	jclass mxtConnTypeClass = (*env)->FindClass(env, "com/atmel/inscreenproximitydemo/device/MxtConnType");

	jmethodID consMxtConnTypeClass =  (*env)->GetMethodID(env,mxtConnTypeClass, "<init>","()V");

	jobject mxtConnTypeObj =  (*env)->NewObject(env,mxtConnTypeClass,consMxtConnTypeClass);

	jmethodID setConnType = (*env)->GetMethodID(env,mxtConnTypeClass, "setConnType","(Ljava/lang/String;)V");
	/*
	(*env)->CallVoidMethod(env,mxtConnTypeObj,setConnType,"conntype");
*/



	//
	int crc = mxt->info.crc;
	jclass mxtInfoClass = (*env)->FindClass(env, "com/atmel/inscreenproximitydemo/device/MxtInfo");
	jmethodID consMxtInfoClass =  (*env)->GetMethodID(env,mxtInfoClass, "<init>","()V");
	jobject mxtInfoObj =  (*env)->NewObject(env,mxtInfoClass,consMxtInfoClass);

	jmethodID setMxtIdInfo = (*env)->GetMethodID(env,mxtInfoClass, "setMxtIdInfo","(Lcom/atmel/inscreenproximitydemo/device/MxtIdInfo;)V");
	(*env)->CallVoidMethod(env,mxtInfoObj,setMxtIdInfo,mxtIdInfoObj);

	//jmethodID setMxtObjects = (*env)->GetMethodID(env,mxtInfoClass, "setMxtObjects","(Ljava/util/ArrayList;)V");
	//(*env)->CallVoidMethod(env,mxtInfoObj,setMxtObjects,mxtObjectObj);

	jmethodID addMxtObject = (*env)->GetMethodID(env,mxtInfoClass, "addMxtObject","(Lcom/atmel/inscreenproximitydemo/device/MxtObject;)V");

	char * p = (char *)(mxt->info.objects);
	int i,type,start_pos_lsb,start_pos_msb,size_minus_one,instances_minus_one,num_report_ids;
	for(i=0;i<num_objects;i++){
		type = p[0];
		start_pos_lsb = p[1];
		start_pos_msb = p[2];
		size_minus_one = p[3];
		instances_minus_one = p[4];
		num_report_ids = p[5];


		p = p + 6;

		jclass mxtObjectClass = (*env)->FindClass(env, "com/atmel/inscreenproximitydemo/device/MxtObject");
		jmethodID consMxtObjectClass =  (*env)->GetMethodID(env,mxtObjectClass, "<init>","()V");
		jobject mxtObjectObj =  (*env)->NewObject(env,mxtObjectClass,consMxtObjectClass);

		jmethodID setType = (*env)->GetMethodID(env,mxtObjectClass, "setType","(I)V");
		(*env)->CallVoidMethod(env,mxtObjectObj,setType,type);

		jmethodID setStartPosLsb = (*env)->GetMethodID(env,mxtObjectClass, "setStartPosLsb","(I)V");
		(*env)->CallVoidMethod(env,mxtObjectObj,setStartPosLsb,start_pos_lsb);

		jmethodID setStartPosMsb = (*env)->GetMethodID(env,mxtObjectClass, "setStartPosMsb","(I)V");
		(*env)->CallVoidMethod(env,mxtObjectObj,setStartPosMsb,start_pos_msb);

		jmethodID setSize = (*env)->GetMethodID(env,mxtObjectClass, "setSize","(I)V");
		(*env)->CallVoidMethod(env,mxtObjectObj,setSize,size_minus_one);

		jmethodID setInstances = (*env)->GetMethodID(env,mxtObjectClass, "setInstances","(I)V");
		(*env)->CallVoidMethod(env,mxtObjectObj,setInstances,instances_minus_one);

		jmethodID setNumberReport = (*env)->GetMethodID(env,mxtObjectClass, "setNumberReport","(I)V");
		(*env)->CallVoidMethod(env,mxtObjectObj,setNumberReport,num_report_ids);

		//
		(*env)->CallVoidMethod(env,mxtInfoObj,addMxtObject,mxtObjectObj);

	}



	jmethodID setCrc = (*env)->GetMethodID(env,mxtInfoClass, "setCrc","(I)V");
	(*env)->CallVoidMethod(env,mxtInfoObj,setCrc,crc);


	//
	jclass mxtDeviceClass =  (*env)->GetObjectClass(env,mxtDevice);
	jmethodID setMxtConnType = (*env)->GetMethodID(env,mxtDeviceClass, "setMxtConnType","(Lcom/atmel/inscreenproximitydemo/device/MxtConnType;)V");
	(*env)->CallVoidMethod(env,mxtDevice,setMxtConnType,mxtConnTypeObj);

	jmethodID setMxtInfo = (*env)->GetMethodID(env,mxtDeviceClass, "setMxtInfo","(Lcom/atmel/inscreenproximitydemo/device/MxtInfo;)V");
	(*env)->CallVoidMethod(env,mxtDevice,setMxtInfo,mxtInfoObj);






	//jfieldID familyId = (*env)->GetFieldID(env,mxtIdInfoClass, "familyId","I");
	//jclass mxtIdInfoClass = (*env)->GetObjectClass(env,mxtIdInfo);
	//jfieldID familyId = (*env)->GetFieldID(env,mxtIdInfoClass, "familyId","I");
	//(*env)->SetObjectField(env,mxtIdInfo,familyId,50);


	rst = (*env)->NewStringUTF(env, "success");
	return rst;
}

