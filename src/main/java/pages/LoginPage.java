package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import base.BaseTest;

public class LoginPage extends BaseTest{
	
	@FindBy(xpath="//input[@id='ap_email']")
	WebElement email_TextBox;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement continue_Button;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement password_TextBox;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	WebElement signIn_Button;
	
	@FindBy(xpath = "//div[@id='auth-error-message-box']//div[@class='a-box-inner a-alert-container']")
    public List<WebElement> incorrectPassword_or_Ph_Message;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	
	 public String login(String username,String password){
	        String message="";
	        email_TextBox.sendKeys(username);
	        logger.info("Enter username");
	        
	        continue_Button.click();
	        if(incorrectPassword_or_Ph_Message.size()!=0){
	            message="Incorrect email or phone number";
	        }
	        else {
	        	password_TextBox.sendKeys(password);
	        	signIn_Button.click();
	            if(incorrectPassword_or_Ph_Message.size()!=0){
	                message="Incorrect password";
	            }
	            else{
	                message=driver.getTitle();
	            }
	        }
	        return message;
	    }

}
