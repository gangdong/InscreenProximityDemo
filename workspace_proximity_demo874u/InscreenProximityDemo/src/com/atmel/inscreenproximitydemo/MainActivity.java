package com.atmel.inscreenproximitydemo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.atmel.inscreenproximitydemo.datatype.NodeMutualDelta;
import com.atmel.inscreenproximitydemo.datatype.XSingleEndDelta;
import com.atmel.inscreenproximitydemo.datatype.YSelfDelta;
import com.atmel.inscreenproximitydemo.device.MxtDevice;
import com.atmel.inscreenproximitydemo.device.MxtIdInfo;
import com.atmel.inscreenproximitydemo.device.MxtObject;
import com.atmel.inscreenproximitydemo.service.T113Handler;
import com.atmel.inscreenproximitydemo.service.T37Handler;
import com.atmel.inscreenproximitydemo.service.T38Handler;
import com.atmel.inscreenproximitydemo.service.T6Handler;
import com.atmel.inscreenproximitydemo.utility.FileHandler;
import com.atmel.inscreenproximitydemo.utility.LightnessControl;
import com.atmel.inscreenproximitydemo.utility.Print;
import com.atmel.inscreenproximitydemo.utility.Utility;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;
/**
 * project:InscreenProximityDemo <BR>
 * class name:MainActivity <BR>
 * @author david.dong
 * create:2015年4月10日上午10:31:34
 */
public class MainActivity extends Activity {

	private Button btnProximity;
	private Button btnSetting;
	private Button btnAbout;
	private EditText etFamilyId;
	private EditText etVariant;
	private EditText etBuild;
	private EditText etVersion;
	private EditText etXSize;
	private EditText etYSize;
	private EditText etObjects;
	private EditText etChecksum;
	private EditText etProximity;
	private EditText etProxAbsThreshold;
	private EditText etProxThreshold;
	private EditText etProxConfidence;
	private EditText etHoverXThreshold;
	private EditText etHoverYThreshold;
	private EditText etHysteresis;
	private EditText etDetectMode;
	private EditText etEdgeSuppression;
	private EditText etThresholdLogo;
	
	
	

	private MaxtouchJni maxtouchJni = new MaxtouchJni();

	static {
		System.loadLibrary("MaxtouchJni");
	}

	
	

	protected static int execRootCmdSilent(String paramString) {
        try {
                Process localProcess = Runtime.getRuntime().exec("su");
                Object localObject = localProcess.getOutputStream();
                DataOutputStream localDataOutputStream = new DataOutputStream((DataOutputStream) localObject);
                String str = String.valueOf(paramString);
                localObject = str + "\n";
                localDataOutputStream.writeBytes((String) localObject);
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit\n");
                localDataOutputStream.flush();
                localProcess.waitFor();
                int result = localProcess.exitValue();
                return (Integer) result;
        } catch (Exception localException) {
                localException.printStackTrace();
                return -1;
        }
}

	
	/*
	private boolean getSysfsPermission(String pkgCodePath){
		
		Process process = null;  
		    DataOutputStream os = null;  
		   try {  
		       //String cmd="chmod 777 " + pkgCodePath; 
			   String cmd = pkgCodePath;
		       process = Runtime.getRuntime().exec("su"); //切换到root帐号  
		       process = Runtime.getRuntime().exec("mkdir /data/data/test2");
		       
		       process.waitFor();  
		   } catch (Exception e) {  
		       return false;  
		   } finally {  
		     try {  
		          if (os != null) {  
		              os.close();  
		          }  
		           process.destroy();  
		       } catch (Exception e) {  
		       }  
		   }  
		  return true;  
	}
	*/
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(ConstantFactory.nTimer!=null){
			ConstantFactory.nTimer.cancel();
			ConstantFactory.nTimer = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		
		etFamilyId = (EditText) this.findViewById(R.id.editTextFamilyID);
		etBuild = (EditText) this.findViewById(R.id.editTextBuild);
		etVersion = (EditText) this.findViewById(R.id.editTextVersion);
		etVariant = (EditText) this.findViewById(R.id.editTextVariant);
		etXSize = (EditText) this.findViewById(R.id.editTextXSize);
		etYSize = (EditText) this.findViewById(R.id.editTextYSize);
		etObjects = (EditText) this.findViewById(R.id.editTextObjects);
		etChecksum = (EditText) this.findViewById(R.id.editTextChecksum);
		
		etProximity = (EditText) this.findViewById(R.id.editTextProximity);
		
		etProxAbsThreshold = (EditText) this.findViewById(R.id.editTextProxAbsThreshold);
		etProxThreshold = (EditText) this.findViewById(R.id.editTextProxThreshold);
		etProxConfidence = (EditText) this.findViewById(R.id.editTextProxConfidence);
		etHoverXThreshold = (EditText) this.findViewById(R.id.editTextHoverXThreshold);
		etHoverYThreshold = (EditText) this.findViewById(R.id.editTextHoverYThreshold);
		etHysteresis = (EditText) this.findViewById(R.id.editTextHysteresis);
		etDetectMode = (EditText) this.findViewById(R.id.editTextDetectMode);
		etEdgeSuppression = (EditText) this.findViewById(R.id.editTextEdgeSuppression);
		
		etThresholdLogo = (EditText) this.findViewById(R.id.editTextLogo10);
		
		btnProximity = (Button) this.findViewById(R.id.button3);
		btnSetting = (Button) this.findViewById(R.id.button2);
		btnAbout = (Button) this.findViewById(R.id.button4);
		
		LinearLayout toparea = (LinearLayout) this.findViewById(R.id.toparealayout);
		toparea.setBackgroundColor(0xff000000);
		
		LinearLayout bottomarea = (LinearLayout) this.findViewById(R.id.bottomarealayout);
		bottomarea.setBackgroundColor(0xff000000);
		
		/* connect device */
		boolean isConnect = maxtouchJni.Scan();
		if (isConnect) {

			
			int ret = maxtouchJni.GetInfoDebug();
			
			String strPath = maxtouchJni.GetSysfsPath();
			
			
			/* read device info */
			boolean isNewDevice = maxtouchJni.GetInfo();
			if (isNewDevice) {
				String rst = maxtouchJni
						.loadMxtDevice(ConstantFactory.mxtDevice);
				if (rst.equals("success")) {
					
					String strFamilyId = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getFamilyId());
					String strVariant = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getVariantId());
					String strVersion = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getVersion());
					String strBuild = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getBuild());
					String strXSize = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo()
									.getMatrixXSize());
					String strYSize = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo()
									.getMatrixYSize());
					String strObjects = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo()
									.getNumberObjects());
					String strChecksum = String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getCrc());
					
					etFamilyId.setText(strFamilyId);
					etBuild.setText(strBuild);
					etVersion.setText(strVersion);
					etVariant.setText(strVariant);
					etXSize.setText(strXSize);
					etYSize.setText(strYSize);
					etObjects.setText(strObjects);
					etChecksum.setText(strChecksum);
					
					/*
					String strMxtDeviceInfo = "family:"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getFamilyId())
							+ "\n"
							+ "variant:"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getVariantId())
							+ "\n"
							+ "version"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getVersion())
							+ "\n"
							+ "build"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo().getBuild())
							+ "\n"
							+ "max x size:"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo()
									.getMatrixXSize())
							+ "\n"
							+ "max y size"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo()
									.getMatrixYSize())
							+ "\n"
							+ "objects:"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getMxtIdInfo()
									.getNumberObjects())
							+ "\n"
							+ "info block crc:"
							+ String.valueOf(ConstantFactory.mxtDevice
									.getMxtInfo().getCrc()) + "\n";
					tv.setText(strMxtDeviceInfo);
					*/
				}
			} else {
				Log.v("mxt status", "get info fail!");
			}
		} else {
			Log.v("mxt status", "probe device fail!");
		}

		Print print = new Print(this);
		T37Handler t37Handler = new T37Handler(ConstantFactory.mxtDevice);
		
		
		// print object table to file
		print.printObjectTable(ConstantFactory.mxtDevice);

		// print mutual reference to file
		t37Handler.readMutualReference(ConstantFactory.mutualReference);
		print.printMutualReference(ConstantFactory.mxtDevice,ConstantFactory.mutualReference);
		
		// print self reference to file
		t37Handler.readSelfReference(ConstantFactory.selfReference);
		print.printSelfReference(ConstantFactory.mxtDevice,ConstantFactory.selfReference);
		
		// print self delta to file
		t37Handler.readSelfDelta(ConstantFactory.selfDelta);
		print.printSelfDelta(ConstantFactory.mxtDevice,ConstantFactory.selfDelta);
		
		// print mutual delta to file
		t37Handler.readMutualDelta(ConstantFactory.mutualDelta);
		print.printMutualDelta(ConstantFactory.mxtDevice,ConstantFactory.mutualDelta);
		
		// print single-end reference to file
		t37Handler.readSEReference(ConstantFactory.singleEndReference);
		print.printSEReference(ConstantFactory.mxtDevice, ConstantFactory.singleEndReference);
		
		// print single-end delta to file
		long startTime = System.nanoTime();
		t37Handler.readSEDelta(ConstantFactory.singleEndDelta);
		long consumingTime = System.nanoTime() - startTime;
		Log.v("Consume time", String.valueOf(consumingTime));
		print.printSEDelta(ConstantFactory.mxtDevice, ConstantFactory.singleEndDelta);
		 

		String strPath = maxtouchJni.GetSysfsDirectory();

		T38Handler t38Handler = new T38Handler(ConstantFactory.mxtDevice);
		/* read configuration information from T38 */
		byte[] t38Register = t38Handler.t38ReadValues(0, 43);
		int[] intValues = new int[21];
		for(int i=0;i<21;i++){
			
			int lsb = t38Register[2*i] & 0xff;
			int msb = t38Register[2*i+1] & 0xff;
			intValues[i] = lsb |( msb << 8);
		}
		
		byte selectedId = t38Register[42];
		byte[] tmpBytes = new byte[14];
		switch(selectedId){
		/* if full screen */
		case 0:
			/* write default value*/
			if((intValues[0]==0)&&(intValues[1]==0)&&(intValues[2]==0)&&(intValues[3]==0)&&(intValues[4]==0)&&(intValues[5]==0)&&(intValues[6]==0)){
				
				tmpBytes[0] = (byte)(100 & 0xff);
				tmpBytes[1] = (byte)((100 >> 8) & 0xff);
				
				tmpBytes[2] = (byte)(60 & 0xff);
				tmpBytes[3] = (byte)((60 >> 8) & 0xff);
				
				tmpBytes[4] = (byte)(3 & 0xff);
				tmpBytes[5] = (byte)((3 >> 8) & 0xff);
				
				tmpBytes[6] = (byte)(300 & 0xff);
				tmpBytes[7] = (byte)((300 >> 8) & 0xff);
				
				tmpBytes[8] = (byte)(300 & 0xff);
				tmpBytes[9] = (byte)((300 >> 8) & 0xff);
				
				tmpBytes[10] = (byte)(10 & 0xff);
				tmpBytes[11] = (byte)((10 >> 8) & 0xff);
				
				tmpBytes[12] = (byte)(0 & 0xff);
				tmpBytes[13] = (byte)((0 >> 8) & 0xff);
				
				t38Handler.t38WriteValues(tmpBytes, 0);
			}
			break;
			/* if top half screen */
		case 1:
			/* write default value */
			if((intValues[7]==0)&&(intValues[8]==0)&&(intValues[9]==0)&&(intValues[10]==0)&&(intValues[11]==0)&&(intValues[12]==0)&&(intValues[13]==0)){
				
				tmpBytes[0] = (byte)(80 & 0xff);
				tmpBytes[1] = (byte)((80 >> 8) & 0xff);
				
				tmpBytes[2] = (byte)(40 & 0xff);
				tmpBytes[3] = (byte)((40 >> 8) & 0xff);
				
				tmpBytes[4] = (byte)(3 & 0xff);
				tmpBytes[5] = (byte)((3 >> 8) & 0xff);
				
				tmpBytes[6] = (byte)(75 & 0xff);
				tmpBytes[7] = (byte)((75 >> 8) & 0xff);
				
				tmpBytes[8] = (byte)(120 & 0xff);
				tmpBytes[9] = (byte)((120 >> 8) & 0xff);
				
				tmpBytes[10] = (byte)(10 & 0xff);
				tmpBytes[11] = (byte)((10 >> 8) & 0xff);
				
				tmpBytes[12] = (byte)(0 & 0xff);
				tmpBytes[13] = (byte)((0 >> 8) & 0xff);
				
				t38Handler.t38WriteValues(tmpBytes, 14);
			}
			break;
			/* if bottom half screen */
		case 2:
			/* write default value */
			if((intValues[14]==0)&&(intValues[15]==0)&&(intValues[16]==0)&&(intValues[17]==0)&&(intValues[18]==0)&&(intValues[19]==0)&&(intValues[20]==0)){
				
				tmpBytes[0] = (byte)(100 & 0xff);
				tmpBytes[1] = (byte)((100 >> 8) & 0xff);
				
				tmpBytes[2] = (byte)(40 & 0xff);
				tmpBytes[3] = (byte)((40 >> 8) & 0xff);
				
				tmpBytes[4] = (byte)(3 & 0xff);
				tmpBytes[5] = (byte)((3 >> 8) & 0xff);
				
				tmpBytes[6] = (byte)(200 & 0xff);
				tmpBytes[7] = (byte)((200 >> 8) & 0xff);
				
				tmpBytes[8] = (byte)(200 & 0xff);
				tmpBytes[9] = (byte)((200 >> 8) & 0xff);
				
				tmpBytes[10] = (byte)(10 & 0xff);
				tmpBytes[11] = (byte)((10 >> 8) & 0xff);
				
				tmpBytes[12] = (byte)(0 & 0xff);
				tmpBytes[13] = (byte)((0 >> 8) & 0xff);
				
				t38Handler.t38WriteValues(tmpBytes, 28);
			}
			break;
			default:
				break;
		}
		
		
		
		LCBtnOnClickListener lcBtnOnClickListener = new LCBtnOnClickListener(this);
		this.btnProximity.setOnClickListener(lcBtnOnClickListener);

		this.btnSetting.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				Intent intent = new Intent(MainActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		
		
		this.btnAbout.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intent);
			}});
		
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	
}

class LCBtnOnClickListener implements Button.OnClickListener{

	private byte gain = 0;
	
	private boolean isInRange = false;
	private boolean isContacted = false;
	
	private boolean isClicked = false;
	
	private int absSeSum = -1;
	private int SeSum = -1;
	
	private int subAbsSeSum = -1;
	private int subSeSum = -1;
	
	private int absHoverXSum = -1;
	private int hoverXSum = -1;
	
	private int subAbsHoverXSum = -1;
	private int subHoverXSum = -1;
	
	private int absHoverYSum = -1;
	private int hoverYSum = -1;
	
	private int subAbsHoverYSum = -1;
	private int subHoverYSum = -1;
	
	
	private int proxAbsThreshold = 100;
	private int proxThreshold = 100;
	private int proxConfidence = 3;
	private int hoverXThreshold = 500;
	private int hoverYThreshold = 500;
	private int proxHysteresis = 200;
	private byte detectMode = 127;
	private int edgeSuppression = 0;
	
	private Utility utility = new Utility();
	private int count=0;
	
	private int countHover = 0;
	
	private Activity act;
	
	T38Handler t38Handler = new T38Handler(ConstantFactory.mxtDevice);
	T37Handler t37Handler = new T37Handler(ConstantFactory.mxtDevice);
	T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
	T113Handler t113Handler = new T113Handler(ConstantFactory.mxtDevice);
	
	private SoundPool soundPool;

	
	LightnessControl lightControl = new LightnessControl();
	
	Vibrator vibrator;
	
	private EditText etProximity;
	
	private EditText etProxAbsThreshold;
	
	private EditText etProxThreshold;
	
	private EditText etProxConfidence;
	
	private EditText etHoverXThreshold;
	
	private EditText etHoverYThreshold;
	
	private EditText etHysteresis;
	
	private EditText etThresholdLogo;
	
	private EditText etDetectMode;
	
	private EditText etEdgeSuppression;
	
	private Button btnSetting;
	
	private Button btnAbout;
	
	private LinearLayout topLayout;
	
	private LinearLayout bottomLayout;
	
	private Button btn;
	
	int baseLineMax;
	int baseLineSum;
	/**
	 * constructor of class:LCBtnOnClickListener <BR>
	 * @author david.dong
	 * create:2015年2月23日下午11:25:42
	 * @param act
	 */
	public LCBtnOnClickListener(Activity act) {
		this.act = act;
		vibrator = (Vibrator) act.getSystemService(Context.VIBRATOR_SERVICE);
		etProximity = (EditText) act.findViewById(R.id.editTextProximity);
		
		etProxAbsThreshold = (EditText) act.findViewById(R.id.editTextProxAbsThreshold);
		etProxThreshold = (EditText) act.findViewById(R.id.editTextProxThreshold);
		etProxConfidence = (EditText) act.findViewById(R.id.editTextProxConfidence);
		etHoverXThreshold = (EditText) act.findViewById(R.id.editTextHoverXThreshold);
		etHoverYThreshold = (EditText) act.findViewById(R.id.editTextHoverYThreshold);
		etHysteresis = (EditText) act.findViewById(R.id.editTextHysteresis);
		etDetectMode = (EditText) act.findViewById(R.id.editTextDetectMode);
		etEdgeSuppression = (EditText) act.findViewById(R.id.editTextEdgeSuppression);
		
		etThresholdLogo = (EditText) act.findViewById(R.id.editTextLogo10);
		
		btnSetting = (Button) act.findViewById(R.id.button2);
		btnAbout = (Button) act.findViewById(R.id.button4);
		
		topLayout = (LinearLayout) act.findViewById(R.id.toparealayout);
		bottomLayout = (LinearLayout) act.findViewById(R.id.bottomarealayout);
		
		//SystemClock.sleep(100);
		
		btn = (Button) act.findViewById(R.id.button3);
		
		soundPool = new SoundPool(10,AudioManager.STREAM_SYSTEM,5);

		soundPool.load(this.act,R.raw.proximity,1);
	}


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//Button btn = (Button) this.act.findViewById(R.id.button1);
		
		if(isClicked){
			isClicked = false;
			btnSetting.setEnabled(true);
			btnAbout.setEnabled(true);
			t113Handler.setSelfXGain(gain);
			
			btn.setText("Start Demo");
			if(ConstantFactory.nTimer!=null){
				ConstantFactory.nTimer.cancel();
				ConstantFactory.nTimer = null;
			}
		}else{
			isClicked = true;
			btnSetting.setEnabled(false);
			btnAbout.setEnabled(false);
			btn.setText("Stop Demo");
			
			int lightness = lightControl.GetLightness(act);
			Log.v("Lightness Control", "lightness is " + String.valueOf(lightness));
			
			int mode = lightControl.getScreenMode(act);
			lightControl.stopAutoBrightness(act);
			
			count = 0;
			countHover = 0;
			gain = t113Handler.getSelfXGain();
			
			/* reset gain */
			byte xGain = 10;
			t113Handler.setSelfXGain(xGain);
			
			/* read configuration value */
			byte[] t38Register = t38Handler.t38ReadValues(0, 43);
			int[] intValues = new int[21];
			for(int i=0;i<21;i++){
				
				int lsb = t38Register[2*i] & 0xff;
				int msb = t38Register[2*i+1] & 0xff;
				intValues[i] = lsb |( msb << 8);
			}
			
			detectMode = t38Register[42];
			/* initialize setting */
			switch(detectMode){
			case 0:
				proxAbsThreshold = intValues[0];
				proxThreshold = intValues[1];
				proxConfidence = intValues[2];
				hoverXThreshold = intValues[3];
				hoverYThreshold = intValues[4];
				proxHysteresis = intValues[5];
				edgeSuppression = intValues[6];
				break;
			case 1:
				proxAbsThreshold = intValues[7];
				proxThreshold = intValues[8];
				proxConfidence = intValues[9];
				hoverXThreshold = intValues[10];
				hoverYThreshold = intValues[11];
				proxHysteresis = intValues[12];
				edgeSuppression = intValues[13];
				break;
			case 2:
				proxAbsThreshold = intValues[14];
				proxThreshold = intValues[15];
				proxConfidence = intValues[16];
				hoverXThreshold = intValues[17];
				hoverYThreshold = intValues[18];
				proxHysteresis = intValues[19];
				edgeSuppression = intValues[20];
				break;
				default:
					break;
			
			}
			
			etProxAbsThreshold.setText(String.valueOf(proxAbsThreshold));
			etProxThreshold.setText(String.valueOf(proxThreshold));
			etProxConfidence.setText(String.valueOf(proxConfidence));
			etHoverXThreshold.setText(String.valueOf(hoverXThreshold));
			etHoverYThreshold.setText(String.valueOf(hoverYThreshold));
			etHysteresis.setText(String.valueOf(proxHysteresis));
			switch(detectMode){
			case 0:
				etDetectMode.setText("Full Screen");
				break;
			case 1:
				etDetectMode.setText("Top Half");
				break;
			case 2:
				etDetectMode.setText("Bottom Half");
				break;
				default:
					break;
			}
			
			etEdgeSuppression.setText(String.valueOf(edgeSuppression));
			/* do calibration before run algorithm */
			t6Handler.t6Calibrate();
			
			
			if(ConstantFactory.nTimer == null){
				ConstantFactory.nTimer = new Timer();
			}
			
			//t37Handler.readSEDelta(ConstantFactory.singleEndDelta);
			//t37Handler.readAllSelfDelta(ConstantFactory.selfDelta, ConstantFactory.singleEndDelta);
			
			/* read raw delta of self-cap */
			t37Handler.readAllSelfDelta(ConstantFactory.selfDelta, ConstantFactory.singleEndDelta, ConstantFactory.hoverDelta);
			ConstantFactory.nTimer.schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					absSeSum = 0;
					subAbsSeSum = 0;
					
					SeSum = 0;
					subAbsSeSum = 0;
					
					absHoverXSum = 0;
					subAbsHoverXSum = 0;
					
					hoverXSum = 0;
					subHoverXSum = 0;
					
					absHoverYSum = 0;
					subAbsHoverYSum = 0;
					
					hoverYSum = 0;
					subHoverYSum = 0;
					
					
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				}}, 0, 100);
		}
		
	}
	
	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case 1:
				if(ConstantFactory.isReadAllSelfDeltaDone){
					long startTime = System.nanoTime();  
					
					/*fetch all raw data from T37*/
					t37Handler.readAllSelfDelta(ConstantFactory.selfDelta, ConstantFactory.singleEndDelta, ConstantFactory.hoverDelta);
					
					
					/* calculate abs sum of single-end y */
					absSeSum = utility.findAbsSumOnSEYDelta(ConstantFactory.singleEndDelta);
					
					/* calculate sum of single end y */
					SeSum = utility.findSumOnSEYDelta(ConstantFactory.singleEndDelta);
					
					/* calculate abs sum of hover x */
					absHoverXSum = utility.findAbsSumOnHoverXDelta(ConstantFactory.hoverDelta);
					
					/* calculate adb sum of hover y*/
					absHoverYSum = utility.findAbsSumOnHoverYDelta(ConstantFactory.hoverDelta);
					
					/* calculate sum of hover x */
					hoverXSum = utility.findSumOnHoverXDelta(ConstantFactory.hoverDelta);
					int tmpHalfXSum = utility.findSumOnHoverXDelta(ConstantFactory.hoverDelta,0,15);
					int tmpBottomXSum = utility.findSumOnHoverXDelta(ConstantFactory.hoverDelta,16,31);
					
					/* calculate sum of hover y */
					hoverYSum = utility.findSumOnHoverYDelta(ConstantFactory.hoverDelta);
					int tmpEdgeYSum = utility.findSumOnHoverYDelta(ConstantFactory.hoverDelta,4,12);
					
					
					int threshold = 30;
					int strength = 9;
					int confirmTime = 3;
					
					
					ArrayList<Integer> indexOverThreshold = utility.findIndexOverThresholdOnSeYDelta(ConstantFactory.singleEndDelta, threshold);
					ArrayList<Integer> indexOverXHThreshold = utility.findIndexOverThresholdOnHoverXDelta(ConstantFactory.hoverDelta, 30);
					ArrayList<Integer> indexOverYHThreshold = utility.findIndexOverThresholdOnHoverYDelta(ConstantFactory.hoverDelta, 30);
					
					
					
					if((absSeSum>proxAbsThreshold)&&(SeSum > proxThreshold)){
						count++;
					}
					else{
						count = 0;
					}
					

					
					if(edgeSuppression==1){
						absHoverYSum = tmpEdgeYSum;
					}else{}
					
					int xHoverOverSize = indexOverXHThreshold.size();
					int yHoverOverSize = indexOverYHThreshold.size();
					
					
					
					switch(detectMode){
					
					/* if full screen mode */
					case 0:
						
						if(((absHoverXSum >hoverXThreshold) && (absHoverYSum > hoverYThreshold))){
							
							countHover ++;
							
						}
						else{
							
							countHover = 0;
						}
						/* if object contacted */
						if((absHoverXSum ==0)&&(absHoverYSum==0)&&(hoverXSum==0)&&(hoverYSum==0)){
							
							/* if face detected */
							if((count>proxConfidence)&&(indexOverThreshold.size()>strength)){
								
								lightControl.SetLightness(act, 0);
								if(isInRange == false){
									isInRange = true;
									vibrator.vibrate(20);
									soundPool.play(1,1, 1, 0, 0, 1);
								}
							}
							else{
								/* finger or face released */
								if((indexOverThreshold.size()<strength-3)){
									
									isInRange = false;
									lightControl.SetLightness(act, 125);
								}
								
							}
							
						}
						else{
							/* proximity */
							if(countHover > 5){
								lightControl.SetLightness(act, 0);
								if(isInRange == false){
									isInRange = true;
									vibrator.vibrate(20);
									soundPool.play(1,1, 1, 0, 0, 1);
								}
							}
							else{
								
									isInRange = false;
									lightControl.SetLightness(act, 125);
							}
						}
						
						
						
						break;
						/* if top half screen mode */
					case 1:
						absHoverXSum = tmpHalfXSum;
						
						/* if hover */
						if(((absHoverXSum >hoverXThreshold) && (absHoverYSum > hoverYThreshold))&&(count>proxConfidence)){
							
							/* if half screen */
							if((xHoverOverSize + yHoverOverSize)>11){
								
								//lightControl.SetLightness(act, 0);
								topLayout.setBackgroundColor(0x00000000);
								if(isInRange == false){
									isInRange = true;
									vibrator.vibrate(20);
									soundPool.play(1,1, 1, 0, 0, 1);
								}
								
							}
							
						}
						else{
							int releashThreshold = proxAbsThreshold - proxHysteresis;
							if(absSeSum < releashThreshold){
								
								isInRange = false;
								topLayout.setBackgroundColor(0xff000000);
								//lightControl.SetLightness(act, 125);
							}
							
						}
						
						/* if object contacted */
						if((absHoverXSum ==0)&&(absHoverYSum==0)&&(hoverXSum==0)&&(hoverYSum==0)){
							
							int topSumX = utility.findAbsSumOnSCTXDelta(ConstantFactory.selfDelta, 0, 16);
							int bottomSumX = utility.findAbsSumOnSCTXDelta(ConstantFactory.selfDelta, 16, 32);
							/* if top half */
							if(topSumX > bottomSumX){
								/* if face detected */
								if((count>proxConfidence)&&(indexOverThreshold.size()>strength)){
									
									topLayout.setBackgroundColor(0x00000000);
									//lightControl.SetLightness(act, 0);
									if(isInRange == false){
										isInRange = true;
										vibrator.vibrate(20);
										soundPool.play(1,1, 1, 0, 0, 1);
									}
								}
								else{
									/* finger or face released*/
									if((indexOverThreshold.size()<strength-3)){
										
										isInRange = false;
										//lightControl.SetLightness(act, 125);
										topLayout.setBackgroundColor(0xff000000);
									}
									
								}
							}
							
						}
						
						break;
						/* if bottom half screen mode */
					case 2:
						absHoverXSum = tmpBottomXSum;
						
						if(((absHoverXSum >hoverXThreshold) && (absHoverYSum > hoverYThreshold))&&(count>proxConfidence)){
							
							
							if((xHoverOverSize + yHoverOverSize)>11){
								
								//lightControl.SetLightness(act, 0);
								bottomLayout.setBackgroundColor(0x00000000);
								if(isInRange == false){
									isInRange = true;
									vibrator.vibrate(20);
									soundPool.play(1,1, 1, 0, 0, 1);
								}
								
							}
							
						}
						else{
							int releashThreshold = proxAbsThreshold - proxHysteresis;
							if(absSeSum < releashThreshold){
								
								isInRange = false;
								//lightControl.SetLightness(act, 125);
								bottomLayout.setBackgroundColor(0xff000000);
							}
							
						}
						
						
						if((absHoverXSum ==0)&&(absHoverYSum==0)&&(hoverXSum==0)&&(hoverYSum==0)){
							
							int topSumX = utility.findAbsSumOnSCTXDelta(ConstantFactory.selfDelta, 0, 16);
							int bottomSumX = utility.findAbsSumOnSCTXDelta(ConstantFactory.selfDelta, 16, 32);
							/* if bottom half */
							if(topSumX < bottomSumX){
								
								if((count>proxConfidence)&&(indexOverThreshold.size()>strength)){
									
									bottomLayout.setBackgroundColor(0x00000000);
									//lightControl.SetLightness(act, 0);
									if(isInRange == false){
										isInRange = true;
										vibrator.vibrate(20);
										soundPool.play(1,1, 1, 0, 0, 1);
									}
								}
								else{
									
									if((indexOverThreshold.size()<strength-3)){
										
										isInRange = false;
										//lightControl.SetLightness(act, 125);
										bottomLayout.setBackgroundColor(0xff000000);
									}
									
								}
							}
							
							
						}
						
						break;
						default:
							break;
					
					}
					
					
					int overThreshold = indexOverThreshold.size();
					etProximity.setText(String.valueOf(absSeSum) + " " + " " + String.valueOf(SeSum) + " " + String.valueOf(absHoverXSum) + " " + String.valueOf(absHoverYSum)+ " " + String.valueOf(count));
					Log.v("absSum","absSum " + String.valueOf(absHoverXSum)+ " " + String.valueOf(absHoverYSum) + " " + String.valueOf(overThreshold)+ " " + String.valueOf(count)+String.valueOf(isContacted));
					
					
					/*calculate execute time */
					long consumingTime = System.nanoTime() - startTime;
					Log.v("execution time",String.valueOf(consumingTime/1000) + "us");
					
					
				}
				break;
			}
			super.handleMessage(msg);
		}

	};
	
}
