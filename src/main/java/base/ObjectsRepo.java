package base;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.ProductSearchResultPage;

public class ObjectsRepo {
	public static Logger logger;
	public static String executionDate;
	public static String executionDateTime;
	public static WebDriver driver;
    public static ExtentTest report;
    public static ExtentReports extent;	
	public static HomePage home_page;
	public static LoginPage login_page;
	public static ProductSearchResultPage productSearchResultPage;
	public static ProductPage productPage;
	public static CartPage cartPage;
}
