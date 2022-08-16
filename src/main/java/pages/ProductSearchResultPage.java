package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import base.BaseTest;

public class ProductSearchResultPage extends BaseTest{
	
//	@FindBy(xpath="(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[1]")
//	WebElement searchResultfirstProduct_link;
	
	@FindBy(xpath="(//a[@class='a-size-base a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']//span[@class='a-price-whole'])[1]")
	WebElement searchResultfirstProductPrice;
	
	public ProductSearchResultPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnFirstProductFromSearchResults() throws InterruptedException {
//		searchResultfirstProduct_link.click();
		Thread.sleep(5);
		
	}
	
	public String getFirstProductPriceFromSearchResults() {
		return searchResultfirstProductPrice.getText();
	}

}
