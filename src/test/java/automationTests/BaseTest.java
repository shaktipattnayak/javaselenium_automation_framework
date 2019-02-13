package automationTests;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import genericLibrary.AppLogger;
import genericLibrary.BrowserSetUp;
import genericLibrary.CommonUtil;
import genericLibrary.EmailSetUp;
import genericLibrary.ExcelLib;
import genericLibrary.TestConfigurationEntities;
import genericLibrary.TestUtil;
import pageLibraries.CommonPages.LogInPage;

public class BaseTest extends BrowserSetUp{
	public static ExcelLib xls = new ExcelLib(CommonUtil.getExcelFilePath());
	public static ExtentReports reports;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlreporter;
	
	@BeforeSuite
	public void beforeSuiteSetUp()
	{
		CommonUtil.createLogFolder();
		htmlreporter=new ExtentHtmlReporter(new File(CommonUtil.getTestExecutionReportFilePath()));
		reports=new ExtentReports();
		reports.setSystemInfo("Enironment", "STG");
		reports.attachReporter(htmlreporter);
		
	}
	@AfterSuite
	public void afterSuiteSetUp() throws Exception
	{
		TestUtil.delayBy(2);
		reports.flush(); 	 	
		//EmailSetUp.sentEmail();

	}
	@BeforeTest
	public void beforeTestSetUp() throws Exception
	{
		AppLogger.log.info("================LogStarted=================");
		base_Driver=getBrowser(TestConfigurationEntities.ChromeBrowser);
		base_Driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		base_Driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		base_Driver.navigate().to(TestConfigurationEntities.STG_URL);
		LogInPage _login=new LogInPage();
		_login.UserLogIn(TestConfigurationEntities.STG_DA_UN, TestConfigurationEntities.STG_DA_PASSWORD);
		
	}
	@AfterTest
	public void afterTestSetUp() throws Exception
	{
		closeBrowser(base_Driver);
		AppLogger.log.info("================LogEnded=================");
	}
	
	@BeforeMethod
	public void beforeMethodSetUp(Method method)
	{
		TestUtil.pageRefresh();
		String testName =method.getName();
		test=reports.createTest(testName);
	}
	
	
	@AfterMethod
	public void afterMethodSetUp(ITestResult result) throws Exception
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.pass(MarkupHelper.createLabel(result.getName()+" test is Passed.",ExtentColor.GREEN));
			AppLogger.log.info(result.getName()+" test is Passed.");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenShotPath=TestUtil.captureScreenShot(result.getName());
			test.fail(MarkupHelper.createLabel(result.getName()+" is failed.", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.addScreenCaptureFromPath(screenShotPath);
			AppLogger.log.info(result.getName()+" test is FAiled.");
			
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.skip((MarkupHelper.createLabel(result.getName()+" is Skipped.", ExtentColor.BLUE)));
			AppLogger.log.info(result.getName()+" is Skipped.");
		}
	}
}
