package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import utilities.Utilities;

public class Amazon_login_test extends BaseTest{
	
	@Test(dataProviderClass=Utilities.class,dataProvider="testdata")
	public void login_test(String username,String password) throws InterruptedException {
        report.log(Status.INFO, "USERNAME:"+username+" PASSWORD:"+password);
		home_page.goToLoginPage();
		//Verifying the title
		String expected_title="Amazon Sign In";
		String actual_title=driver.getTitle();
		Assert.assertEquals(expected_title, actual_title);
		
		report.log(Status.INFO, "Login Page Title: "+actual_title);
		report.log(Status.PASS, "Login Page Title Validation Success");
		logger.info("Login Page Title: "+actual_title);
		logger.info("Login Page Title Validation Success");
		
		String login_message=login_page.login(username, password);
		
		if(login_message.contains("Incorrect")){
            report.log(Status.INFO,login_message);
            report.pass(login_message+" alert is expected for invalid username or password");
        }
        else {
        	report.log(Status.INFO,"Home Page Title After Login: "+login_message);
        	report.pass("Login successful");
        }
		
		
				
	
	}

	
}
