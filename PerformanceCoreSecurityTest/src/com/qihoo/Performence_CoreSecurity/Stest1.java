package com.qihoo.Performence_CoreSecurity;

import org.junit.Test;

import com.jayway.android.robotium.solo.Solo;
import com.qihoo.avMobile.support.PropertiesManager;
import com.qihoo.avMobile.support.ReportFrame;

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
	private static final String PACKAGE_NAME = PropertiesManager.getInstance().getProperty("PACKAGE");
	private static final String LAUNCH_ACTIVITY_NAME = "com.qihoo.antivirus.ui.index.AppEnterActivity"; 
	private Solo solo;
	
	static {//创建异常
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
	    solo = new Solo(getInstrumentation(), getActivity());
	    ReportFrame.CreatCaseReport("test");
	}
	
	//测试用例实现
	@Test
	public void testCase1() throws Exception {
		ReportFrame.putsStep("测试点击防通知打扰后进入防通知打扰页");
		solo.sleep(2000);
		solo.waitForText("防通知打扰");
		solo.clickOnText("防通知打扰");
		ReportFrame.writeReport(solo, "测试点击防通知打扰后进入防通知打扰页", solo.searchText("软件通知管理"), true);
	}

	
	
	public void tearDown() throws Exception{
		solo.finishOpenedActivities();
		ReportFrame.closeCaseReport();
		super.tearDown();
	}
	

}
