/**
 * project:InscreenProximityDemo <BR>
 * file name:FileHandler.java <BR>
 * @author david.dong
 * create:2015年2月17日下午11:52:54
 * 
 */
package com.atmel.inscreenproximitydemo.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

/**
 * project:InscreenProximityDemo <BR>
 * class name:FileHandler <BR>
 * @author david.dong
 * create:2015年2月17日下午11:52:54
 */
public class FileHandler {

	Context context;
	
	
	/**
	 * constructor of class:FileHandler <BR>
	 * @author david.dong
	 * create:2015年2月18日上午12:00:50
	 * @param context
	 */
	public FileHandler(Context context) {
		
		this.context = context;
	}


	public void save(String filename,String content){
		
		FileOutputStream outputStream;
		
		try {
			//the path is /data/data/com.atmel.inscreenproximitydemo/files/
			String filePath = context.getFilesDir().getPath().toString() + filename;
			File file = new File(filePath);
			if(!file.exists()){
				
				file.createNewFile();
			}
			
			//outputStream = context.openFileOutput(filePath, Context.MODE_APPEND);
			outputStream = new FileOutputStream(file);
			outputStream.write(content.getBytes());
			outputStream.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
