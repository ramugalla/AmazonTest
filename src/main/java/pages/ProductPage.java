package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;

public class ProductPage extends BaseTest{
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	WebElement addToCart_Button;
	
	@FindBy(xpath="//div[@id='attachDisplayAddBaseAlert']/span")
	WebElement AddToCartSuccessMessage;
	
	@FindBy(xpath="//a[@id='attach-close_sideSheet-link']")
	WebElement CloseAddCartSuccessMeassgeElement;
	
	@FindBy(xpath="//span[@id='productTitle']")
	WebElement productTitle_Element;
	
	@FindBy(xpath="//span[@class='a-price a-text-price a-size-medium apexPriceToPay']/span[2]")
	WebElement productPrice;
	
	public ProductPage() {
		PageFactory.initElements(driver, this);
		
	}

	
	public void clickOnAddToCartButton() throws InterruptedException {
		
		addToCart_Button.click();
		Thread.sleep(5);
	}
	
	public String getAddToCartSuccessMessage() throws InterruptedException {
		Thread.sleep(5);
		return AddToCartSuccessMessage.getText();
	}
	
	public void closeAddCartSuccessMessage() throws InterruptedException {
		CloseAddCartSuccessMeassgeElement.click();
		Thread.sleep(3);
	}
	
	public String getProductTitle() {
		
		return productTitle_Element.getText();
		
	}
	
	public String getProductPrice() throws InterruptedException {
		Thread.sleep(5);
		return productPrice.getText();
		 
	}
}
