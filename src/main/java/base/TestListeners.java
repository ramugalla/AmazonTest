package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utilities.Utilities;

public class TestListeners extends ObjectsRepo implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		
		report=extent.createTest(result.getMethod().getMethodName());
		logger.info("********* "+result.getMethod().getMethodName()+" Started *********");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		report.log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+" is Passed");
		logger.info("********* "+result.getMethod().getMethodName()+" Success *********");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		report.log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+" is Failed");
		report.log(Status.FAIL, result.getThrowable());
		logger.info("********* "+result.getMethod().getMethodName()+" Failed *********");
		logger.error(result.getThrowable());
		
		
		//Take and add screenshot to report where execution is failed
		Date date=new Date();
		SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		String Datetime=format.format(date);
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File("./Results/Screenshot/"+result.getMethod().getMethodName()+"_"+Datetime+"_Failed.jpeg");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String screenShotPath="./Results/Screenshot/"+result.getMethod().getMethodName()+"_"+Datetime+"_Failed.jpeg";
		report.addScreenCaptureFromPath(screenShotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		
		
		Date date1=new Date();
		SimpleDateFormat format_With_Date =new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat format_With_Sec =new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		executionDate=format_With_Date.format(date1);
		executionDateTime=format_With_Sec.format(date1);
		File fileWithDate=new File("./Results/"+executionDate);
		File fileWithTime=new File("./Results/"+executionDate+"/"+executionDateTime);

		
		
		
		if(!fileWithDate.exists()) {
			System.out.println("Creatring Date folder under Results");
			fileWithDate.mkdir();
		}
		if(!fileWithTime.exists()) {
			System.out.println("Creatring Date with Time folder under Results");
			
			fileWithTime.mkdir();
		}
		
		//Log4j Initialization
		System.setProperty("log4j.configurationFile","./log4j.properties");
		logger=LogManager.getLogger(TestListeners.class);
		logger.info("Execution Started at "+executionDateTime);
		
		
		
		//It will execute before actual execution starts
		extent=ExtentSetup.setupExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
