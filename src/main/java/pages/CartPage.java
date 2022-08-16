package pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;
import utilities.Utilities;

public class CartPage extends BaseTest{
	
	@FindBy(xpath="//span[@class='a-truncate-cut']")
	List<WebElement> productTitle_ListElements;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement deleteProductFromCart_link;
	
	@FindBy(xpath="//span[@class='a-price sc-product-price']//span[@class='a-price-whole']")
	WebElement productPrice;
	
	@FindBy(xpath="//span[@id='sc-subtotal-amount-buybox']//span[@class='a-price-whole']")
	WebElement subTotal;
	
	@FindBy(xpath="//span[@class='a-price sc-product-price']//span[@class='a-price-whole']")
	List<WebElement> productPriceList_Elements;
	
	public CartPage() {
		PageFactory.initElements(driver,this);
	}
	
	public List<String> getListOfProductTitlesInCart() throws InterruptedException {
		Thread.sleep(3000);
		
		return Utilities.getListOfElementText(productTitle_ListElements);
		
	}
	
	public void clickOnDeletFromCart() throws InterruptedException {
		Thread.sleep(3000);
		deleteProductFromCart_link.click();
		Thread.sleep(3000);
	}
	
	public String getProductPrice() throws InterruptedException {
		Thread.sleep(5000);
		return productPrice.getText();
	}
	
	public int addPricesOfAllItems() throws InterruptedException {
		Thread.sleep(3000);
		int total_price=0;
		Iterator<WebElement> itr = productPriceList_Elements.iterator();
		while(itr.hasNext()) {
			total_price=total_price+Utilities.convertStringToInt(itr.next().getText());
		}
		return total_price;
		
	}
	
	public int getSubTotal() {
		return Utilities.convertStringToInt(subTotal.getText());
	}
}
