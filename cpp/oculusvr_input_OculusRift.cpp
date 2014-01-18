#include <jni.h>  
#include <iostream> 
#include <conio.h>
 //#include <stdio.h>  
 #include "OVR.h"
 #include "oculusvr_input_OculusRift.h"  
 using namespace OVR;  
 using namespace std;  
 Ptr<DeviceManager>     pManager;  
 Ptr<HMDDevice>     pHMD;  
 Ptr<SensorDevice>     pSensor;  
 SensorFusion*     pFusionResult;  
 HMDInfo     Info;  
 bool InfoLoaded;  
 //#define STD_GRAV 9.81 // What SHOULD work with Rift, but off by 1000  
 #define STD_GRAV 0.00981 // This gives nice 1.00G on Z with Rift face down !!!  
 JNIEXPORT jboolean JNICALL Java_oculusvr_input_OculusRift_initialize  
  (JNIEnv *env, jobject thisObj) {  
   printf("Initializing Rift...\n");  
   System::Init();

	pFusionResult = new SensorFusion();
      pManager = *DeviceManager::Create();  

      pHMD = *pManager->EnumerateDevices<HMDDevice>().CreateDevice();  
      if (pHMD)
	  {  
           printf("pHMD created\n");  
           InfoLoaded = pHMD->GetDeviceInfo(&Info);  
           pSensor = *pHMD->GetSensor();
      }  
      else{  
		  printf("No HMD, creating sensor");
           pSensor = *pManager->EnumerateDevices<SensorDevice>().CreateDevice();  
      }
      if (pSensor)  
      {  
           printf("Attaching sensor\n");  
           pFusionResult->AttachToSensor(pSensor);
      }  else {
		  printf("Failed to init sensor\n");
	  }
   return pSensor != NULL;  
 }  
 JNIEXPORT void JNICALL Java_oculusvr_input_OculusRift_destroy  
      (JNIEnv *env, jobject thisObj) {  
           pSensor.Clear();
			pHMD.Clear();
           pManager.Clear();
		   delete pFusionResult;
           OVR::System::Destroy();  
		   printf("Cleaning up\n");  
           return;  
 }  
 JNIEXPORT jfloatArray JNICALL Java_oculusvr_input_OculusRift_update  
  (JNIEnv *env, jobject thisObj) {  
   jfloat data[6];  
   Vector3f acc=pFusionResult->GetAcceleration();
   Quatf quaternion = pFusionResult->GetOrientation();
   // yaw, pitch, roll  
   quaternion.GetEulerAngles<Axis_Y, Axis_X, Axis_Z>(&data[2], &data[1], &data[0]);  
   data[3] = acc.x / STD_GRAV;  
   data[4] = acc.y / STD_GRAV;  
   data[5] = acc.z / STD_GRAV;  
   jfloatArray result;  
   result = env->NewFloatArray(6);  
   if (result == NULL) {  
     return NULL; /* out of memory error thrown */  
   }  
   env->SetFloatArrayRegion(result, 0, 6, data);  
   return result;  
 }  
 JNIEXPORT jint JNICALL Java_oculusvr_input_OculusRift_getHResolution  
  (JNIEnv *env, jobject thisObj) {  
   return Info.HResolution;  
 }  
 JNIEXPORT jint JNICALL Java_oculusvr_input_OculusRift_getVResolution  
  (JNIEnv *env, jobject thisObj) {  
   return Info.VResolution;  
 }  
 JNIEXPORT jfloat JNICALL Java_oculusvr_input_OculusRift_getHScreenSize  
  (JNIEnv *env, jobject thisObj) {  
   return Info.HScreenSize;  
 }  
 JNIEXPORT jfloat JNICALL Java_oculusvr_input_OculusRift_getVScreenSize  
  (JNIEnv *env, jobject thisObj) {  
   return Info.VScreenSize;  
 }  
 JNIEXPORT jfloat JNICALL Java_oculusvr_input_OculusRift_getVScreenCenter  
  (JNIEnv *env, jobject thisObj) {  
   return Info.VScreenCenter;  
 }  
 JNIEXPORT jfloat JNICALL Java_oculusvr_input_OculusRift_getEyeToScreenDistance  
  (JNIEnv *env, jobject thisObj) {  
   return Info.EyeToScreenDistance;  
 }  
 JNIEXPORT jfloat JNICALL Java_oculusvr_input_OculusRift_getLensSeparationDistance  
  (JNIEnv *env, jobject thisObj) {  
   return Info.LensSeparationDistance;  
 }  
 JNIEXPORT jfloat JNICALL Java_oculusvr_input_OculusRift_getInterpupillaryDistance  
  (JNIEnv *env, jobject thisObj) {  
   return Info.InterpupillaryDistance;  
 }  
 JNIEXPORT jfloatArray JNICALL Java_oculusvr_input_OculusRift_getDistortionK  
  (JNIEnv *env, jobject thisObj) {  
   jfloat* distortion = Info.DistortionK;  
   jfloatArray result;  
   result = env->NewFloatArray(6);  
   if (result == NULL) {  
     return NULL; /* out of memory error thrown */  
   }  
   env->SetFloatArrayRegion(result, 0, 6, distortion);  
   return result;  
 }  
 JNIEXPORT jint JNICALL Java_oculusvr_input_OculusRift_getDesktopX  
  (JNIEnv *env, jobject thisObj) {  
   return Info.DesktopX;  
 }  
 JNIEXPORT jint JNICALL Java_oculusvr_input_OculusRift_getDesktopY  
  (JNIEnv *env, jobject thisObj) {  
   return Info.DesktopY;  
 }  
 JNIEXPORT jstring JNICALL Java_oculusvr_input_OculusRift_getDisplayDeviceName  
  (JNIEnv *env, jobject thisObj) {  
   char *name = Info.DisplayDeviceName;  
   jstring result = env->NewStringUTF(name);  
   return result;  
 }  
 JNIEXPORT jlong JNICALL Java_oculusvr_input_OculusRift_getDisplayId  
  (JNIEnv *env, jobject thisObj) {  
   return Info.DisplayId;  
 }  

 JNIEXPORT jfloatArray JNICALL Java_oculusvr_input_OculusRift_getOrientation
  (JNIEnv *env, jobject thisObj){
	  jfloat data[4];  
   Quatf quaternion = pFusionResult->GetOrientation();
 
   data[0] = quaternion.x;
   data[1] = quaternion.y;  
   data[2] = quaternion.z;
   data[3] = quaternion.w;
   jfloatArray result;  
   result = env->NewFloatArray(4);  
   if (result == NULL) {  
     return NULL; /* out of memory error thrown */  
   }  
   env->SetFloatArrayRegion(result, 0, 4, data);  
   return result;  
 }

 JNIEXPORT jfloatArray JNICALL Java_oculusvr_input_OculusRift_getAcceleration
  (JNIEnv *env, jobject thisObj){
	  jfloat data[3];  
   Vector3f acc=pFusionResult->GetAcceleration();
   data[0] = acc.x;  
   data[1] = acc.y;  
   data[2] = acc.z;
   jfloatArray result;
   result = env->NewFloatArray(3);
   if (result == NULL) {
     return NULL; /* out of memory error thrown */  
   }
   env->SetFloatArrayRegion(result, 0, 3, data);
   return result;
 }

 