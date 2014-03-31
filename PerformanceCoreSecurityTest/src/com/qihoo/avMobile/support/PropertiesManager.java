package com.qihoo.avMobile.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.util.Log;

public class PropertiesManager {
	
	public static Properties p;
	public static File pFile = new File("D:\\Myspace\\PerformanceCoreSecurityTest\\resource\\config.properties");
	public static PropertiesManager pt;
	
	public static PropertiesManager getInstance(){
		if(pt == null){
			pt = new PropertiesManager();
		}
		return pt;
	}

	
	public PropertiesManager(){
		
		Log.e("ABSOLUTEPATH", pFile.getPath());
		
		FileInputStream  pInputStream = null;
		try{
			 pInputStream = new FileInputStream(pFile);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		p = new Properties();
		try{
			p.load(pInputStream);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public String getProperty(String key){	
		return p.getProperty(key);
	}


}
