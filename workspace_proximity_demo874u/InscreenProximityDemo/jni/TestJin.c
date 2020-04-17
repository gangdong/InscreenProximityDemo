/*
 * TestJin.c
 *
 *  Created on: 2015Äê2ÔÂ13ÈÕ
 *      Author: david.dong
 */

#include <string.h>
#include <jni.h>

#include "com_atmel_inscreenproximitydemo_TestJni.h"
#include "libmaxtouch.h"
#include "src/main_sub.h"
JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_TestJni_getString
  (JNIEnv * env, jobject this){


	jstring str = (*env)->NewStringUTF(env, "the first program of JNI by David !");
	return str;
}


JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_TestJni_getName
  (JNIEnv * env, jobject this){

	int num = mxt_success();
	jstring str = "";
	if(num > 15){

		str = (*env)->NewStringUTF(env, "the success num is > 15 !");
	}
	else{

		str = (*env)->NewStringUTF(env, "the success num is < 15 !");
	}

	//jstring str = (*env)->NewStringUTF(env, "my name is david !");
	return str;
}

int mxt_old(){

	return 18;
}
