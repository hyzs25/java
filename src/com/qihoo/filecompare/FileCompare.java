package com.qihoo.filecompare;

import java.io.File;
import com.qihoo.filecompare.*;

public class FileCompare{
	
	public static String oldApk = PropertiesManager.getInstance().getProperty("OLD_APK");
	public static String newApk = PropertiesManager.getInstance().getProperty("NEW_APK");
	public static String oldApkDir = PropertiesManager.getInstance().getProperty("OLD_APK_DIR");
	public static String newApkDir = PropertiesManager.getInstance().getProperty("NEW_APK_DIR");
//	public static String bootDir = oldApkDir.split("\\\\")[0];
	public static String reportPath = PropertiesManager.getInstance().getProperty("REPORT_PATH");
	
	
	
	public static void main(String[] args) throws Exception{
		
//		
//		System.out.println(oldApkDir);
//		System.out.println(newApkDir);
//		System.out.println(bootDir);
		
		ToolUtil.deleteExistFile(oldApkDir, newApkDir, reportPath);
		
		String oldZipFile = ToolUtil.RenameFile(oldApk);
		String newZipFile = ToolUtil.RenameFile(newApk);
		
		Double oldSize = ToolUtil.getFileSize(new File(oldZipFile));
		Double newSize = ToolUtil.getFileSize(new File(newZipFile));		
		System.out.println(oldSize);
		System.out.println(newSize);
		
		ToolUtil.unZip(oldZipFile, oldApkDir);
		ToolUtil.unZip(newZipFile, newApkDir);

		ToolUtil.runCommend();
		System.out.println("正在生成报告................");
	    ReportFrame.CreatCaseReport(oldApk, newApk, oldSize, newSize);	
	    
		ReportFrame.putsStep("新增加的文件列表");	
		ContentSpy.putAddMessage(reportPath);
		
		ReportFrame.putsStep("修改过的文件列表");
		ReportFrame.putsChildStep("修改过size增加的文件列表");
		ContentSpy.putModifyAddMessage(reportPath);
		ReportFrame.putsChildStep("修改过size减少的文件列表");
		ContentSpy.putModifyReduceMessage(reportPath);
		ReportFrame.putsChildStep("修改后size不变的文件列表");
		ContentSpy.putModifyEqualMessage(reportPath);
		if (ContentSpy.ModifyStep == 0) {
			ReportFrame.putNullCntent("不存在被修改的文件列表");
		}
		
		ReportFrame.putsStep("被删除的文件列表");	
		ContentSpy.putDelMessage(reportPath);
			
		ReportFrame.closeCaseReport();			
		
		
		
	}

}
