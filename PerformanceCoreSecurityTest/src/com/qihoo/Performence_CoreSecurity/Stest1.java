package com.qihoo.Performence_CoreSecurity;

import org.junit.Test;


import com.qihoo.avMobile.support.PropertiesManager;
import com.qihoo.avMobile.support.ReportFrame;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo.Config;
import com.robotium.solo.Solo.Config.ScreenshotFileType;


import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;


/**
 * @author heyang-ms
 *
 */
public class Stest1 extends ActivityInstrumentationTestCase2 {
	
	
	@SuppressWarnings("unchecked")
	public Stest1(Class activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	private static Class launcherActivityClass;
	private static final String PACKAGE_NAME = "com.qihoo.antivirus";
	private static final String LAUNCH_ACTIVITY_NAME = "com.qihoo.antivirus.ui.index.AppEnterActivity"; 
	private Solo solo;
	
	static {//�����쳣
		try{  
			launcherActivityClass = Class.forName(LAUNCH_ACTIVITY_NAME);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Stest1() throws ClassNotFoundException{
		super(PACKAGE_NAME, launcherActivityClass);
	}

	public void setUp() throws Exception{
		super.setUp();
		
		//robotium 5.0.1���ϰ汾֧��
		Config config = new Config();
		config.timeout_large = 3000;
		config.screenshotFileType = ScreenshotFileType.PNG;
		config.screenshotSavePath = ReportFrame.folderPath + "/ScreenCaptrue/";
	    solo = new Solo(getInstrumentation(), config);
	    getActivity();
	    ReportFrame.CreatCaseReport("test");
	}
	
	//��������ʵ��
	@Test
	public void testCase1() throws Exception {
		String caseName = "���Ե����֪ͨ���ź�����֪ͨ����ҳ";
		ReportFrame.putsStep(caseName);		
		solo.sleep(2000);
		solo.waitForText("��֪ͨ����");
		solo.clickOnText("��֪ͨ����");		
		ReportFrame.writeReport(solo, caseName, solo.searchText("���֪ͨ����"), true);
		solo.takeScreenshot(caseName);
	}

	
	
	public void tearDown() throws Exception{
		solo.finishOpenedActivities();
		ReportFrame.closeCaseReport();
		super.tearDown();
	}
	

}
