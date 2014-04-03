package com.qihoo.filecompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.qihoo.filecompare.*;



public class ReportFrame {
	
	
    public static String repotHtml = "";
	public static Date dt = new Date();
	public static String folderName = getFolderName();
	public static String folderPath = getFolderPath();
	public static Double moSize;

	public static String CreatCaseReport(String olderApkName,String newerApkName, double oldApkSize, double newApkSize) throws IOException{
		
		String olderVersion = getVersion(olderApkName);
		String newerVersion = getVersion(newerApkName);
		String modifyContent = "";
		
		DecimalFormat df   = new DecimalFormat("###,##0.00");  
		
		
		
		
		if (newApkSize > oldApkSize) {
			 moSize = newApkSize - oldApkSize;
			 String modifySize = df.format(moSize);
			 modifyContent = "增加了" + modifySize + "kb";
		}else if (newApkSize == oldApkSize) {
			modifyContent = "apk Size没有改变";
		}else {
			 moSize = oldApkSize - newApkSize;
			 String modifySize = df.format(moSize);
			 modifyContent = "减少了" + modifySize + "kb";
		}

		SimpleDateFormat ftime = new SimpleDateFormat("HHmmss");
				 
		String caseName = "对比报告" + ftime.format(dt) + ".html";

		File folder = new File(folderPath);
		
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		repotHtml = folderPath + "/" + caseName;

		
		String caseContent = "<html><head>"+
        		"<meta http-equiv='Content-Type' content='text/html; charset=gbk'>" +
        		"<title>QA Test Report</title>" +
        		"<style type=text/css>" +
        		".title { font-family: verdana; font-size: 30px;  font-weight: bold; align: left; color: #045AFD;}" +
        		".bold_text { font-family: verdana; font-size: 12px;  font-weight: bold;}" +
        		".normal_text { font-family: verdana; font-size: 12px;  font-weight: normal;}" +
        		".small_text { font-family: verdana; font-size: 10px;  font-weight: normal; }" +
        		".border { border: 1px solid #045AFD;}" +
        		".border_null { width: 100%; border: 0px; height: 30px;}" +
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
        		"<td align=right><p class=title>APK file Compare Report</p></td>" +
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
        		"<td width=20%><p class=bold_text>测试目的</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=75%><p class=normal_text>apk文件对比测试</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>测试日期</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=75%><p class=normal_text>"+ folderName +"</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>旧APK版本</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=5%><p class=normal_text>" + olderVersion + "(文件大小： " + oldApkSize +  "kb)</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>新APK版本</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=5%><p class=normal_text>" + newerVersion + "(文件大小： " + newApkSize +  "kb)</p></td>" +
        		"</tr>" +
        		"<tr>" +
        		"<tr>" +
        		"<td width=20%><p class=bold_text>总大小差异</p></td>" +
        		"<td width=5%><p class=bold_text>:</p></td>" +
        		"<td width=5%><p class=normal_text>" + modifyContent + "</p></td>" +
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
        		"<td class=border_left ><p class=bold_text align='center'>状态</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>变更的文件</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>原文件size(kb)</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>现文件size(kb)</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>文件size增量(kb)</p></td>" +
        		"<td class=border_left ><p class=bold_text align='center'>Test Time(hhmmss)</p></td>" +
        		"</tr>";
		
		File htmlFile = new File(repotHtml);
		FileWriter writeFile = new FileWriter(htmlFile);
		writeFile.append(caseContent);
		writeFile.close();
		
		return repotHtml;
	}
	
	private static String getVersion(String apkName) {
		apkName = apkName.replace(".apk", "");
		String[] apkPathArray = apkName.split("\\\\");

		String VersionName = apkPathArray[apkPathArray.length - 1];
		
		return VersionName;
	}


	public static void putsStep(String stepDesc){
		String stepContent = "<tr class=border_null></tr><tr>" +
				"<td class=border_left colspan='6' ><p class=normal_text align='left'><font color='#FF0000'><b>"+ stepDesc +"</b></p></td>" +
				"</tr>";
    	try{
	        FileWriter addWrite = new FileWriter(repotHtml, true);
	        addWrite.append(stepContent);//写入Html
	        addWrite.close();
        }catch(IOException e) {
 		   e.printStackTrace();
        }
	}
	
	public static void putsChildStep(String stepDesc){
		String stepContent = "<tr>" +
				"<td class=border_left colspan='6' ><p class=normal_text align='left'><font color='#FF0000'><b>"+ stepDesc +"</b></p></td>" +
				"</tr>";
    	try{
	        FileWriter addWrite = new FileWriter(repotHtml, true);
	        addWrite.append(stepContent);//写入Html
	        addWrite.close();
        }catch(IOException e) {
 		   e.printStackTrace();
        }
	}
	
	public static void putNullCntent(String stepDesc){
		String stepContent = "<tr>" +
				"<td class=border_left colspan='6' ><p class=normal_text align='center'><font color='#FF0000'>"+ stepDesc +"</p></td>" +
				"</tr>";
    	try{
	        FileWriter addWrite = new FileWriter(repotHtml, true);
	        addWrite.append(stepContent);//写入Html
	        addWrite.close();
        }catch(IOException e) {
 		   e.printStackTrace();
        }
	}
	
	
	public static void writeReport(String tag, String changedFile, float oldSize, float newSize){
		

		Date nowTime = new Date();
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("HH:mm:ss");
		String stepTime = sdDateFormat.format(nowTime);
		
			
		//文件增量
			float ff = newSize - oldSize;
			DecimalFormat fnum = new DecimalFormat("##0.00"); 
			String dd=fnum.format(ff); 
			
			float fileChangedSize = Float.parseFloat(dd);
			
			String ps = "";
			if(tag.equals("增加文件")){
				 ps = "<tr>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ tag +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ changedFile +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ oldSize +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ newSize +"</p></td>" +
		    			"<td class=border_left ><p class=result_ok align='center'><font color='#00CC33'>"+ fileChangedSize +"</font></p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
		    			"</tr>";
			}else if (tag.equals("修改文件")) {
				 ps = "<tr>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ tag +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ changedFile +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ oldSize +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ newSize +"</p></td>" +
		    			"<td class=border_left ><p class=result_ok align='center'><font color='#FF9933'>"+ fileChangedSize +"</font></p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
		    			"</tr>";
			}else {
				 ps = "<tr>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ tag +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ changedFile +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ oldSize +"</p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ newSize +"</p></td>" +
		    			"<td class=border_left ><p class=result_ok align='center'><font color='#CC0000'>"+ fileChangedSize +"</font></p></td>" +
		    			"<td class=border_left ><p class=normal_text align='center'>"+ stepTime +"</p></td>" +
		    			"</tr>";
			}
			

			
			try{
	    		FileWriter addWrite = new FileWriter(repotHtml, true);  //追加
	    		addWrite.append(ps);
	    		addWrite.close();
	        }catch(IOException e) {
	 		   e.printStackTrace();
	        }
	}
		
	
	public static void closeCaseReport(){
		String closeContent = "</tr></table><br/><br/><br/><br/></body></html>";
    	try{
	        FileWriter closeWrite = new FileWriter(repotHtml, true);
	        closeWrite.append(closeContent);//写入Html
	        closeWrite.close();
	        System.out.println("Everything is OK!!!!-----------------------------------------------------");
        }catch(IOException e) {
 		   e.printStackTrace();
	    }
	}
	
	
	
	public static String getFolderPath(){
		String reportDir =  ".";
		String folderPath = reportDir + "/APKCompareReport/" + folderName;
		return folderPath;
	}
	
	public static String getFolderName(){
		SimpleDateFormat fdate = new SimpleDateFormat("yyyyMMdd");
		String fn = fdate.format(dt);
		return fn;
	}

}
