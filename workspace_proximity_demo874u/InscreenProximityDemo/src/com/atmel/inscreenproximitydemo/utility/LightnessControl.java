/**
 * project:InscreenProximityDemo <BR>
 * file name:LightnessControl.java <BR>
 * @author david.dong
 * create:2015年2月23日下午11:14:40
 * 
 */
package com.atmel.inscreenproximitydemo.utility;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.provider.Settings.System;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * project:InscreenProximityDemo <BR>
 * class name:LightnessControl <BR>
 * 
 * @author david.dong create:2015年2月23日下午11:14:40
 */
public class LightnessControl {

	// 判断是否开启了自动亮度调节
	public  boolean isAutoBrightness(Activity act) {
		boolean automicBrightness = false;
		ContentResolver aContentResolver = act.getContentResolver();
		try {
			automicBrightness = Settings.System.getInt(aContentResolver,
					Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
		} catch (Exception e) {
			Toast.makeText(act, "无法获取亮度", Toast.LENGTH_SHORT).show();
		}
		return automicBrightness;
	}

	// 改变亮度
	public  void SetLightness(Activity act, int value) {
		try {
			System.putInt(act.getContentResolver(), System.SCREEN_BRIGHTNESS,
					value);
			WindowManager.LayoutParams lp = act.getWindow().getAttributes();
			lp.screenBrightness = (value <= 0 ? 1 : value) / 255f;
			act.getWindow().setAttributes(lp);
		} catch (Exception e) {
			Toast.makeText(act, "无法改变亮度", Toast.LENGTH_SHORT).show();
		}
	}

	// 获取亮度
	public  int GetLightness(Activity act) {
		return System.getInt(act.getContentResolver(),
				System.SCREEN_BRIGHTNESS, -1);
	}

	// 停止自动亮度调节
	public  void stopAutoBrightness(Activity activity) {
		Settings.System.putInt(activity.getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS_MODE,
				Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	}

	// 开启亮度自动调节
	public  void startAutoBrightness(Activity activity) {
		Settings.System.putInt(activity.getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS_MODE,
				Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	}

	/**
	 * 获得当前屏幕亮度的模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
	 */

	public int getScreenMode(Activity act) {
		int screenMode = 0;
		try {
			screenMode = Settings.System.getInt(act.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE);
		} catch (Exception localException) {

		}
		return screenMode;
	}

	/**
	 * 设置当前屏幕亮度的模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
	 */
	public void setScreenMode(Activity act, int paramInt) {
		try {
			Settings.System.putInt(act.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE, paramInt);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

}
