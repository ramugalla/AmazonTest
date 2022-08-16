package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentSetup extends ObjectsRepo {
	
	public static ExtentReports setupExtentReport() {
		
		ExtentSparkReporter spartkReport=new ExtentSparkReporter("./Results/"+executionDate+"/"+executionDateTime+"/ExtentReport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spartkReport);
		spartkReport.config().setDocumentTitle("Extent Report");
		spartkReport.config().setTheme(Theme.DARK);
		spartkReport.config().setReportName("Amazon Test Report");
		return extent;
		
		
	}

}
