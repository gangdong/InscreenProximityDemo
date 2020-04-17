/**
 * project:InscreenProximityDemo <BR>
 * file name:AboutActivity.java <BR>
 * @author david.dong
 * create:2015年4月10日上午10:31:34
 * 
 */
package com.atmel.inscreenproximitydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * project:InscreenProximityDemo <BR>
 * class name:AboutActivity <BR>
 * @author david.dong
 * create:2015年4月10日上午10:31:34
 */
public class AboutActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
	}

	
	
}
