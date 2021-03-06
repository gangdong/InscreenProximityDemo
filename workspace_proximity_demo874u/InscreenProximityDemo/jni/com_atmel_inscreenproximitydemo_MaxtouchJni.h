/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_atmel_inscreenproximitydemo_MaxtouchJni */

#ifndef _Included_com_atmel_inscreenproximitydemo_MaxtouchJni
#define _Included_com_atmel_inscreenproximitydemo_MaxtouchJni
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    Scan
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_Scan
  (JNIEnv *, jobject);

/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    GetInfo
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetInfo
  (JNIEnv *, jobject);

/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    GetInfoDebug
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetInfoDebug
  (JNIEnv *, jobject);

/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    GetSysfsPath
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetSysfsPath
  (JNIEnv *, jobject);

/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    GetSysfsDirectory
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_GetSysfsDirectory
  (JNIEnv *, jobject);

/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    ReadRegister
 * Signature: (II)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_ReadRegister
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    WriteRegister
 * Signature: (I[B)I
 */
JNIEXPORT jint JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_WriteRegister
  (JNIEnv *, jobject, jint, jbyteArray);

/*
 * Class:     com_atmel_inscreenproximitydemo_MaxtouchJni
 * Method:    loadMxtDevice
 * Signature: (Lcom/atmel/inscreenproximitydemo/device/MxtDevice;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_atmel_inscreenproximitydemo_MaxtouchJni_loadMxtDevice
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif
