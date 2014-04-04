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
		System.out.println("�������ɱ���................");
	    ReportFrame.CreatCaseReport(oldApk, newApk, oldSize, newSize);	
	    
		ReportFrame.putsStep("�����ӵ��ļ��б�");	
		ContentSpy.putAddMessage(reportPath);
		
		ReportFrame.putsStep("�޸Ĺ����ļ��б�");
		ReportFrame.putsChildStep("�޸Ĺ�size���ӵ��ļ��б�");
		ContentSpy.putModifyAddMessage(reportPath);
		ReportFrame.putsChildStep("�޸Ĺ�size���ٵ��ļ��б�");
		ContentSpy.putModifyReduceMessage(reportPath);
		ReportFrame.putsChildStep("�޸ĺ�size������ļ��б�");
		ContentSpy.putModifyEqualMessage(reportPath);
		if (ContentSpy.ModifyStep == 0) {
			ReportFrame.putNullCntent("�����ڱ��޸ĵ��ļ��б�");
		}
		
		ReportFrame.putsStep("��ɾ�����ļ��б�");	
		ContentSpy.putDelMessage(reportPath);
			
		ReportFrame.closeCaseReport();			
		
		
		
	}

}
