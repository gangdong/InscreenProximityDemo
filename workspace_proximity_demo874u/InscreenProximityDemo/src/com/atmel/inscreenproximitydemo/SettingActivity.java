/**
 * project:InscreenProximityDemo <BR>
 * file name:SettingActivity.java <BR>
 * @author david.dong
 * create:2015年4月1日上午11:42:04
 * 
 */
package com.atmel.inscreenproximitydemo;

import com.atmel.inscreenproximitydemo.service.T38Handler;
import com.atmel.inscreenproximitydemo.service.T6Handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * project:InscreenProximityDemo <BR>
 * class name:SettingActivity <BR>
 * @author david.dong
 * create:2015年4月1日上午11:42:04
 */
public class SettingActivity extends Activity {

	
	private EditText etPara1;
	private EditText etPara2;
	private EditText etPara3;
	private EditText etPara4;
	private EditText etPara5;
	private EditText etPara6;
	private Button btnWrite;
	
	private Spinner spinner1;
	private ArrayAdapter<CharSequence> spinnerArray = null;
	
	private CheckBox cbEdgeSuppression;
	
	int[] intValues = new int[21];
	byte selectedID = 0;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		
		etPara1 = (EditText) this.findViewById(R.id.editTextPara1Set);
		etPara2 = (EditText) this.findViewById(R.id.editTextPara2Set);
		etPara3 = (EditText) this.findViewById(R.id.editTextPara3Set);
		etPara4 = (EditText) this.findViewById(R.id.editTextPara4Set);
		etPara5 = (EditText) this.findViewById(R.id.editTextPara5Set);
		etPara6 = (EditText) this.findViewById(R.id.editTextPara6Set);
		
		btnWrite = (Button) this.findViewById(R.id.buttonWriteSetting);
		
		cbEdgeSuppression = (CheckBox) this.findViewById(R.id.checkBox1);
		
		spinner1 = (Spinner) this.findViewById(R.id.spinner1);
		spinnerArray = ArrayAdapter.createFromResource(this, R.array.spinnername,android.R.layout.simple_spinner_dropdown_item);// 实例化ArrayAdapter
		spinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(spinnerArray);
		
		spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				T38Handler t38Handler = new T38Handler(ConstantFactory.mxtDevice);
				byte[] tmpValues = t38Handler.t38ReadValues(0, 43);
				
				for(int i=0;i<21;i++){
					
					int lsb = tmpValues[2*i] & 0xff;
					int msb = tmpValues[2*i+1] & 0xff;
					intValues[i] = lsb |( msb << 8);
				}
				
				switch((int)id){
				
				case 0:
					etPara1.setText(String.valueOf(intValues[0]));
					etPara2.setText(String.valueOf(intValues[1]));
					etPara3.setText(String.valueOf(intValues[2]));
					etPara4.setText(String.valueOf(intValues[3]));
					etPara5.setText(String.valueOf(intValues[4]));
					etPara6.setText(String.valueOf(intValues[5]));
					
					if(intValues[6]==1){
						cbEdgeSuppression.setChecked(true);
					}else{
						cbEdgeSuppression.setChecked(false);
					}
					
					break;
				case 1:
					etPara1.setText(String.valueOf(intValues[7]));
					etPara2.setText(String.valueOf(intValues[8]));
					etPara3.setText(String.valueOf(intValues[9]));
					etPara4.setText(String.valueOf(intValues[10]));
					etPara5.setText(String.valueOf(intValues[11]));
					etPara6.setText(String.valueOf(intValues[12]));
					
					if(intValues[13]==1){
						cbEdgeSuppression.setChecked(true);
					}else{
						cbEdgeSuppression.setChecked(false);
					}
					
					break;
				case 2:
					etPara1.setText(String.valueOf(intValues[14]));
					etPara2.setText(String.valueOf(intValues[15]));
					etPara3.setText(String.valueOf(intValues[16]));
					etPara4.setText(String.valueOf(intValues[17]));
					etPara5.setText(String.valueOf(intValues[18]));
					etPara6.setText(String.valueOf(intValues[19]));
					
					if(intValues[20]==1){
						cbEdgeSuppression.setChecked(true);;
					}else{
						cbEdgeSuppression.setChecked(false);;
					}
					
				 	break;
				 	default:
				 		break;
				}
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}});
		
		
		T38Handler t38Handler = new T38Handler(ConstantFactory.mxtDevice);
		
		byte[] tmpValues = t38Handler.t38ReadValues(0, 43);
		
		for(int i=0;i<21;i++){
			
			int lsb = tmpValues[2*i] & 0xff;
			int msb = tmpValues[2*i+1] & 0xff;
			intValues[i] = lsb |( msb << 8);
		}
		
		selectedID = tmpValues[42];
		
		switch(selectedID){
		
		case 0:
			etPara1.setText(String.valueOf(intValues[0]));
			etPara2.setText(String.valueOf(intValues[1]));
			etPara3.setText(String.valueOf(intValues[2]));
			etPara4.setText(String.valueOf(intValues[3]));
			etPara5.setText(String.valueOf(intValues[4]));
			etPara6.setText(String.valueOf(intValues[5]));
			
			if(intValues[6]==1){
				cbEdgeSuppression.setChecked(true);
			}else{
				cbEdgeSuppression.setChecked(false);
			}
			
			break;
		case 1:
			etPara1.setText(String.valueOf(intValues[6]));
			etPara2.setText(String.valueOf(intValues[7]));
			etPara3.setText(String.valueOf(intValues[8]));
			etPara4.setText(String.valueOf(intValues[9]));
			etPara5.setText(String.valueOf(intValues[10]));
			etPara6.setText(String.valueOf(intValues[11]));
			
			if(intValues[13]==1){
				cbEdgeSuppression.setChecked(true);
			}else{
				cbEdgeSuppression.setChecked(false);
			}
			break;
		case 2:
			etPara1.setText(String.valueOf(intValues[12]));
			etPara2.setText(String.valueOf(intValues[13]));
			etPara3.setText(String.valueOf(intValues[14]));
			etPara4.setText(String.valueOf(intValues[15]));
			etPara5.setText(String.valueOf(intValues[16]));
			etPara6.setText(String.valueOf(intValues[17]));
			
			if(intValues[20]==1){
				cbEdgeSuppression.setChecked(true);
			}else{
				cbEdgeSuppression.setChecked(false);
			}
			break;
			
		default:
			break;
			
		}
		
		spinner1.setSelection(selectedID, true);

		WriteBtnOnClickListener writeBtnOnClickListener = new WriteBtnOnClickListener(this);
	
		this.btnWrite.setOnClickListener(writeBtnOnClickListener);
		
		//spinner1.setd
	
	}
}

class WriteBtnOnClickListener implements Button.OnClickListener{

	private Activity act;
	
	
	/**
	 * constructor of class:WriteBtnOnClickListener <BR>
	 * @author david.dong
	 * create:2015年4月1日下午2:52:55
	 * @param act
	 */
	public WriteBtnOnClickListener(Activity act) {
		this.act = act;
	}


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText etPara1 = (EditText) this.act.findViewById(R.id.editTextPara1Set);
		EditText etPara2 = (EditText) this.act.findViewById(R.id.editTextPara2Set);
		EditText etPara3 = (EditText) this.act.findViewById(R.id.editTextPara3Set);
		EditText etPara4 = (EditText) this.act.findViewById(R.id.editTextPara4Set);
		EditText etPara5 = (EditText) this.act.findViewById(R.id.editTextPara5Set);
		EditText etPara6 = (EditText) this.act.findViewById(R.id.editTextPara6Set);
		Spinner spn = (Spinner) this.act.findViewById(R.id.spinner1);
		
		CheckBox etPara7 = (CheckBox) this.act.findViewById(R.id.checkBox1);
		
		byte[] selectId = new byte[1];
		byte[] tmpBytes = new byte[14];
		
		int intPara1 = Integer.parseInt(etPara1.getText().toString());
		int intPara2 = Integer.parseInt(etPara2.getText().toString());
		int intPara3 = Integer.parseInt(etPara3.getText().toString());
		int intPara4 = Integer.parseInt(etPara4.getText().toString());
		int intPara5 = Integer.parseInt(etPara5.getText().toString());
		int intPara6 = Integer.parseInt(etPara6.getText().toString());
		
		boolean isChecked = etPara7.isChecked();
		
		int intPara7;
		
		if(isChecked){
			intPara7 = 1;
		}else{
			intPara7 = 0;
		}
		

		tmpBytes[0] = (byte)(intPara1 & 0xff);
		tmpBytes[1] = (byte)((intPara1 >> 8) & 0xff);
		
		tmpBytes[2] = (byte)(intPara2 & 0xff);
		tmpBytes[3] = (byte)((intPara2 >> 8) & 0xff);
		
		tmpBytes[4] = (byte)(intPara3 & 0xff);
		tmpBytes[5] = (byte)((intPara3 >> 8) & 0xff);
		
		tmpBytes[6] = (byte)(intPara4 & 0xff);
		tmpBytes[7] = (byte)((intPara4 >> 8) & 0xff);
		
		tmpBytes[8] = (byte)(intPara5 & 0xff);
		tmpBytes[9] = (byte)((intPara5 >> 8) & 0xff);
		
		tmpBytes[10] = (byte)(intPara6 & 0xff);
		tmpBytes[11] = (byte)((intPara6 >> 8) & 0xff);
		
		tmpBytes[12] = (byte)(intPara7 & 0xff);
		tmpBytes[13] = (byte)((intPara7 >> 8) & 0xff);
		
		T38Handler t38Handler = new T38Handler(ConstantFactory.mxtDevice);
		
		int id = (int) spn.getSelectedItemId();
		int index = -1;
		switch(id){
		
		case 0:
			index = 0;
			break;
		case 1:
			index = 14;
			break;
		case 2:
			index = 28;
			break;
		default:
			break;
		}
		
		t38Handler.t38WriteValues(tmpBytes, index);
		
		selectId[0] = (byte) id;
		
		t38Handler.t38WriteValues(selectId, 42);
		
		T6Handler t6Handler = new T6Handler(ConstantFactory.mxtDevice);
		
		t6Handler.t6Backup();
	}
	
	
}