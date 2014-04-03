package com.qihoo.filecompare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.qihoo.filecompare.*;

public class ContentSpy {
	
	public static int ModifyStep = 0;
	
	
	public static void putAddMessage(String reportFile) throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(reportFile)));
		int step = 0;
		String line = null;
		while((line = br1.readLine())!= null){
			if (line.contains("<<")) {
				step += 1;
				String addFilename = transArray(line)[1];
				float addFileSize = generateSize(transArray(line)[2]);
				ReportFrame.writeReport("Add", addFilename, 0, addFileSize);
			}										
		}
		if (step == 0) {
			ReportFrame.putNullCntent("没有新增加的文件-------------------------");
		}
		br1.close();
	}
	
	public static void putModifyAddMessage(String reportFile) throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(reportFile)));

		String line = null;
		
		while((line = br1.readLine())!= null){
			if (line.contains("<>")) {
				ModifyStep+=1;
				String[] lineArray = transArray(line);
				if (lineArray[0].equals(lineArray[5])) {
					String modifyFileName = lineArray[0];
					int oldInitFileSize = generateInitSize(lineArray[1]);
					int newInitFileSize = generateInitSize(lineArray[6]);
					float oldFileSize = generateSize(lineArray[1]);
					float newFileSize = generateSize(lineArray[6]);
					float updateSize = newFileSize - oldFileSize;
					
					if (oldInitFileSize < newInitFileSize) {
						ReportFrame.writeReport("Modify", modifyFileName, oldFileSize, newFileSize);
					}
			   } 
		    }
		}
//		if (step == 0) {
//			ReportFrame.putNullCntent("没有修改增大的的文件-------------------------");
//		}
		br1.close();
	}
	
	public static void putModifyReduceMessage(String reportFile) throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(reportFile)));

		String line = null;
		
		while((line = br1.readLine())!= null){
			if (line.contains("<>")) {
				ModifyStep+=1;
				String[] lineArray = transArray(line);
				if (lineArray[0].equals(lineArray[5])) {
					String modifyFileName = lineArray[0];
					int oldInitFileSize = generateInitSize(lineArray[1]);
					int newInitFileSize = generateInitSize(lineArray[6]);
					float oldFileSize = generateSize(lineArray[1]);
					float newFileSize = generateSize(lineArray[6]);
					
					if (oldInitFileSize > newInitFileSize) {
						ReportFrame.writeReport("Modify", modifyFileName, oldFileSize, newFileSize);
					}
			   } 
		    }
		}
//		if (step == 0) {
//			ReportFrame.putNullCntent("没有修改减小的的文件-------------------------");
//		}
		br1.close();
	}
	
	public static void putModifyEqualMessage(String reportFile) throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(reportFile)));

		String line = null;
		
		while((line = br1.readLine())!= null){
			if (line.contains("<>")) {
				ModifyStep+=1;
				String[] lineArray = transArray(line);
				if (lineArray[0].equals(lineArray[5])) {
					String modifyFileName = lineArray[0];
					int oldInitFileSize = generateInitSize(lineArray[1]);
					int newInitFileSize = generateInitSize(lineArray[6]);
					float oldFileSize = generateSize(lineArray[1]);
					float newFileSize = generateSize(lineArray[6]);
					
					if (oldInitFileSize == newInitFileSize) {
						ReportFrame.writeReport("Modify", modifyFileName, oldFileSize, newFileSize);
					}
			   } 
		    }
		}
//		if (step == 0) {
//			ReportFrame.putNullCntent("没有修改减小的的文件-------------------------");
//		}
		br1.close();
	}
	

	
	
	public static void putDelMessage(String reportFile) throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(reportFile)));
		int step = 0;

		String line = null;
		while((line = br1.readLine())!= null){
			if (line.contains(">>")) {
				step+=1;
				String delFileName = transArray(line)[0];
				float delFileSize = generateSize(transArray(line)[1]);
				ReportFrame.writeReport("Delete", delFileName, delFileSize, 0);
			}										
		}
		if (step == 0) {
			ReportFrame.putNullCntent("没有被删除的文件-------------------------");
		}
		br1.close();
	}
	
	
	
//	public static void addMessage(String line){
//
////		System.out.println("???????????" + addFilename + ", ?????С???" + addFileSize + "kb");
//		
//	}	
	
//	private static void updateMessage(String line) {
//		String[] lineArray = transArray(line);
////		?????????????????????±?
////		for(int q=0;q< lineArray.length;q++){
////			System.out.println(lineArray[q]);
////		}
//		
//		//?????????????????????????????????
//		if (lineArray[0].equals(lineArray[5])) {
//			String updateFileName = lineArray[0];
//			int oldFileSize = generateSize(lineArray[1]);
//			int newFileSize = generateSize(lineArray[6]);
//			int updateSize = newFileSize - oldFileSize;
//			
//			if (updateSize != 0) {
//				ReportFrame.writeReport("修改文件", updateFileName, oldFileSize, newFileSize);
//			}
//			
////			if(oldFileSize < newFileSize){
////				int updateSize = newFileSize - oldFileSize;
////				System.out.println("?????????? " + updateFileName + ", ?????????" + updateSize + "kb" );
////			}else{
////				int updateSize = oldFileSize - newFileSize;
////				System.out.println("?????????? " + updateFileName + ", ?????????" + updateSize + "kb" );
////			}
//		}
//		
//		
//	}
//	private static void delMessage(String line) {
//		String delFileName = transArray(line)[0];
//		int delFileSize = generateSize(transArray(line)[1]);
//		ReportFrame.writeReport("删除文件", delFileName, delFileSize, 0);
////		System.out.println("??????????" + delFileName + ", ?????С???" + generateSize(transArray(line)[1])  + "kb");
//		
//	}


	
	/**
	 * 
	 * @param line，读取的每一行
	 * @description:读取每一行后用空格分隔
	 */
	public static String[] transArray(String initString){
		String gInitString = initString.replaceAll("\\||\\+", "");
		String[] sar = gInitString.split(" ");
		
	    List<String> tmp = new ArrayList<String>();  
        for(String str:sar){  
            if(str!=null && str.length()!=0){  
                tmp.add(str);
            }
        }
        sar = tmp.toArray(new String[0]); 
        for(String bString:sar){
//        	System.out.println(bString);
        }
        return sar;
	}

	public static int generateInitSize(String size){

		String intSize = size.replaceAll(",|/|:", "");

		int i = Integer.parseInt(intSize);
				
		return i;
	}
	
	//?????size??????????????????int
	public static float generateSize(String size){

		String floatSize = size.replaceAll(",|/|:", "");

		float i = Float.parseFloat(floatSize);
		float f = i/1024;
		
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		String dd=fnum.format(f); 
		
		float ff = Float.parseFloat(dd);
		return ff;
	}
	
	
	public static void main(String[] args) throws IOException{
		String s = "ds ffds|+g fgd";
		String[] aStrings = transArray(s);
		for(String c:aStrings){
			System.out.println(c);
		}
		
	}
}
