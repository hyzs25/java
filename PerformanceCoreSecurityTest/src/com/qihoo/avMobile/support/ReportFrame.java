package com.qihoo.avMobile.support;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.robotium.solo.Solo;



import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

public class ReportFrame {
	
	public static final String  PACKAGE_NAME = "com.qihoo.antivirus";
	
	public static int testStep = 0;
	public static int PassNum = 0;
	public static int FailNum = 0;
    public static String caseHtml = "";
	public static Date dt = new Date();
	public static String folderName = getFolderName();
	public static String folderPath = getFolderPath();
	
	@SuppressLint("SimpleDateFormat")
	public static String CreatCaseReport(String filename) throws IOException{
		


		SimpleDateFormat ftime = new SimpleDateFormat("HHmmss");
				 
		String caseName = filename + "_" + ftime.format(dt) + ".html";

		File folder = new File(folderPath);
		
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		caseHtml = folderPath + "/" + caseName;
		Log.e(PACKAGE_NAME, "文件名路径是" + caseHtml);

		
		String caseContent = "<html><head>"+
        		"<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>" +
        		"<title>QA Test Report</title>" +
        		"<style type=text/css>" +
        		".title { font-family: verdana; font-size: 30px;  font-weight: bold; align: left; color: #045AFD;}" +
        		".bold_text { font-family: verdana; font-size: 12px;  font-weight: bold;}" +
        		".normal_text { font-family: verdana; font-size: 12px;  font-weight: normal;}" +
        		".small_text { font-family: verdana; font-size: 10px;  font-weight: normal; }" +
        		".border { border: 1px solid #045AFD;}" +
        		".border_left { border: 1px solid #045AFD}" +
        		".border_right { border-top: 1px solid #045AFD; border-right: 1px solid #045AFD;}" +
        		".result_ok { font-family: verdana; font-size: 12px;  font-weight: bold; text-align: center;color: green;}" +
        		".result_nok { font-family: verdana; font-size: 12px;  font-weight: bold; text-align: center;color: red;}" +
        		"</style>" +
        		"</head>" +
        		"<body>" +
        		"<br>" +
        		"<center>" +
        		"<table width=800 border=0 cellpadding=2 cellspacing=2>" +
        		"<tbody>" +
        		"<tr>" +
        		"<td>" +
        		"<table width=100% border=0 cellpadding=2 cellspacing=2>" +
        		"<tbody>" +
        		"<tr>" +
        		"<td style=width: 150px;>&nbsp;</td>" +
        		"<td align=right><p class=title>Android Auto Test Report</p></td>" +
        		"</tr>" +
        		"</tbody>" +
        		"</table>" +
        		"<br>" +
        		"<hr width=100% class=border size=1px>" +
        		"<br>" +
        		"<br>" +
        		"<center>" +
        		"<table id='info' border=0 width=95% cellpadding=2 cellspacing=2>" +
        		"<tbody>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>Test Case</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=75%><p class=normal_text>" + filename + "</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>Test Date</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=75%><p class=normal_text>"+ folderName +"</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>Test Model</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=5%><p class=normal_text>" + Build.MODEL + "</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>Test Reason</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=5%><p class=normal_text>Function Test</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		//"<td width=20%><p class=bold_text>ccccccccccccc</p></td>" +
        		//"<td width=5%><p class=bold_text></p>eeeeeeeeeeee</td>" +
        		//"<td id='case_time' width=5%><p class=normal_text>ffffffffffffff</p></td>" +
        		"</tr>" +
        		"</tbody>" +
        		"</table>" +
        		"</center>" +
        		"<br>" +
        		"</br>" +
        		"<center>" +
        		"<table border=0 width=95% cellpadding=2 cellspacing=0>" +
        		"<tbody>" +
        		"<tr>" +
        		"<td class=border_left ><p class=bold_text align='center'>Test Step</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>Check Point</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>Actual Result</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>Expected Result</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>Test Result</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>Test Time(hhmmss)</p></td>" +
        		"</tr>";
		
		File htmlFile = new File(caseHtml);
		FileWriter writeFile = new FileWriter(htmlFile);
		writeFile.append(caseContent);
		writeFile.close();
		
		return caseHtml;
	}
	
	public static void putsStep(String stepDesc){
		String stepContent = "<tr>" +
				"<td class=border_left colspan='6' ><p class=normal_text align='center'><font color='#FF0000'>"+ stepDesc +"</p></td>" +
				"</tr>";
    	try{
	        FileWriter addWrite = new FileWriter(caseHtml, true);
	        addWrite.append(stepContent);//写入Html
	        addWrite.close();
        }catch(IOException e) {
 		   e.printStackTrace();
        }
	}
	
	
	public static void writeReport(Solo solo, String checkPoint,String actual, String expect){
		
		testStep += 1;
		Date nowTime = new Date();
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("HH:mm:ss");
		String stepTime = sdDateFormat.format(nowTime);
		
		if(actual == expect||actual.matches(expect)){
			PassNum += 1;
			Log.e(PACKAGE_NAME,"通过点个数: "+PassNum);
			
			String checkPointState = "pass";
			
			String ps = "<tr>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ testStep +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ checkPoint +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ actual +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ expect +"</p></td>" +
	    			"<td class=border_left ><p class=result_ok align='center'><a href = '../TestCapture/"+ checkPoint +".jpg' target ='_blank'><font color='#00CC33'>"+ checkPointState.toLowerCase() +"</font></a></p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
	    			"</tr>";
			
			try{
	    		FileWriter addWrite = new FileWriter(caseHtml, true);  //追加
	    		addWrite.append(ps);
	    		addWrite.close();
	        }catch(IOException e) {
	 		   e.printStackTrace();
	        }
		}else {
			FailNum += 1;
			Log.e(PACKAGE_NAME, "失败点个数： " + FailNum);
			
			String checkPointState = "fail";
			
			String fs = "<tr>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ testStep +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ checkPoint +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ actual +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ expect +"</p></td>" +
	    			"<td class=border_left ><p class=result_ok align='center'><a href = '../TestCapture/" + checkPoint + ".jpg' target ='_blank'><font color='#FF0000'>"+ 
	    			checkPointState.toLowerCase()+ "</font></a></p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
	    			"</tr>";
			
			try{
	    		FileWriter addWrite = new FileWriter(caseHtml, true);  //追加
	    		addWrite.append(fs);
	    		addWrite.close();
	        }catch(IOException e) {
	 		   e.printStackTrace();
	        }
		}
	}

	

	
	public static void writeReport(Solo solo, String checkPoint,Boolean actual, Boolean expect){
		
		testStep += 1;
		Date nowTime = new Date();
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("HH:mm:ss");
		String stepTime = sdDateFormat.format(nowTime);
		
		if(actual == expect){
			PassNum += 1;
			Log.e(PACKAGE_NAME,"通过点个数: "+PassNum);
			
			String checkPointState = "pass";
			
			String ps = "<tr>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ testStep +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ checkPoint +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ actual +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ expect +"</p></td>" +
	    			"<td class=border_left ><p class=result_ok align='center'><a href = '../TestCapture/"+ checkPoint +".jpg' target ='_blank'><font color='#00CC33'>"+ checkPointState.toLowerCase() +"</font></a></p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
	    			"</tr>";
			
			try{
	    		FileWriter addWrite = new FileWriter(caseHtml, true);  //追加
	    		addWrite.append(ps);
	    		addWrite.close();
	        }catch(IOException e) {
	 		   e.printStackTrace();
	        }
		}else {
			FailNum += 1;
			Log.e(PACKAGE_NAME, "失败点个数： " + FailNum);
			
			String checkPointState = "fail";
			
			String fs = "<tr>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ testStep +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ checkPoint +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ actual +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ expect +"</p></td>" +
	    			"<td class=border_left ><p class=result_ok align='center'><a href = '../TestCapture/" + checkPoint + ".jpg' target ='_blank'><font color='#FF0000'>"+ 
	    			checkPointState.toLowerCase()+ "</font></a></p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
	    			"</tr>";
			
			try{
	    		FileWriter addWrite = new FileWriter(caseHtml, true);  //追加
	    		addWrite.append(fs);
	    		addWrite.close();
	        }catch(IOException e) {
	 		   e.printStackTrace();
	        }
		}
	}
	

	public static void writeReport(Solo solo, String checkPoint,int actual, int expect){
		
		testStep += 1;
		Date nowTime = new Date();
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("HH:mm:ss");
		String stepTime = sdDateFormat.format(nowTime);
		
		if(actual == expect){
			PassNum += 1;
			Log.e(PACKAGE_NAME,"通过点个数: "+PassNum);
			
			String checkPointState = "pass";
			
			String ps = "<tr>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ testStep +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ checkPoint +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ actual +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ expect +"</p></td>" +
	    			"<td class=border_left ><p class=result_ok align='center'><a href = '../TestCapture/"+ checkPoint +".jpg' target ='_blank'><font color='#00CC33'>"+ checkPointState.toLowerCase() +"</font></a></p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
	    			"</tr>";
			
			try{
	    		FileWriter addWrite = new FileWriter(caseHtml, true);  //追加
	    		addWrite.append(ps);
	    		addWrite.close();
	        }catch(IOException e) {
	 		   e.printStackTrace();
	        }
		}else {
			FailNum += 1;
			Log.e(PACKAGE_NAME, "失败点个数： " + FailNum);
			
			String checkPointState = "fail";
			
			String fs = "<tr>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ testStep +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ checkPoint +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ actual +"</p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ expect +"</p></td>" +
	    			"<td class=border_left ><p class=result_ok align='center'><a href = '../TestCapture/" + checkPoint + ".jpg' target ='_blank'><font color='#FF0000'>"+ 
	    			checkPointState.toLowerCase()+ "</font></a></p></td>" +
	    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
	    			"</tr>";
			
			try{
	    		FileWriter addWrite = new FileWriter(caseHtml, true);  //追加
	    		addWrite.append(fs);
	    		addWrite.close();
	        }catch(IOException e) {
	 		   e.printStackTrace();
	        }
		}
	}
	
	public static void closeCaseReport(){
		String closeContent = "</tr></table>";
    	try{
	        FileWriter closeWrite = new FileWriter(caseHtml, true);
	        closeWrite.append(closeContent);//写入Html
	        closeWrite.close();
        }catch(IOException e) {
 		   e.printStackTrace();
	    }
	}
	
	
	
	//获取sd卡初始路径
	public static String getSDPath(){
		
		File baseDir = null;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			baseDir = Environment.getExternalStorageDirectory();
		}
		Log.e(PACKAGE_NAME, "SD卡根目录是" + baseDir.toString());
		return baseDir.toString();	
	}
	
	public static String getFolderPath(){
		String sdDir =  getSDPath();
		Log.e("folderName", "---------------------" + folderName);
		String folderPath = sdDir + "/AndroidAutoReport/" + folderName;
		return folderPath;
	}
	
	public static String getFolderName(){
		SimpleDateFormat fdate = new SimpleDateFormat("yyyyMMdd");
		String fn = fdate.format(dt);
		return fn;
	}

}
