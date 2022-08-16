package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import utilities.Utilities;

public class Cart_test extends BaseTest{
	
	@Test(priority=1,dataProviderClass=Utilities.class,dataProvider="testdata")
	public void addToCart_Test(String product) throws InterruptedException {
		String ProductTitle=add_product_to_cart(product);
		home_page.goToCart();
		List<String> cartProductTitleList=cartPage.getListOfProductTitlesInCart();
		Boolean ProductStatusOnCartPage=cartProductTitleList.contains(ProductTitle);
		Assert.assertTrue(ProductStatusOnCartPage);
		report.log(Status.PASS, "Add To cart success");
		
		
	}
	
	@Test(priority=2,dataProviderClass=Utilities.class,dataProvider="testdata")
	public void RemoveFromCart_Test(String product) throws InterruptedException {
		String ProductTitle=add_product_to_cart(product);
		home_page.goToCart();
		List<String> cartProductTitleList=cartPage.getListOfProductTitlesInCart();
		Boolean ProductStatusOnCartPage=cartProductTitleList.contains(ProductTitle);
		Assert.assertTrue(ProductStatusOnCartPage);
		report.log(Status.INFO, "Added item to cart");
		cartPage.clickOnDeletFromCart();
		List<String> AftercartProductTitleList=cartPage.getListOfProductTitlesInCart();
		Boolean AfterProductStatusOnCartPage=AftercartProductTitleList.contains(ProductTitle);
		Assert.assertFalse(AfterProductStatusOnCartPage);
		report.log(Status.PASS, "Succefully deleted the product from cart");
		
		
	}
	
	@Test(priority=3,dataProviderClass=Utilities.class,dataProvider="testdata")
	public void product_Price_Validation(String product) throws InterruptedException {
			home_page.searchProduct(product);
			Thread.sleep(5);
			
			productSearchResultPage.clickOnFirstProductFromSearchResults();
			Utilities.changeWindowToChild(driver);
			String ProductPrice=productPage.getProductPrice();
			String ProductTitle=productPage.getProductTitle();
			report.log(Status.INFO, "Product Title: "+ProductTitle);
			productPage.clickOnAddToCartButton();

			productPage.closeAddCartSuccessMessage();
		    home_page.goToCart();
		    String ActualProductPrice=cartPage.getProductPrice();
		    int ActualPrice=Utilities.convertStringToInt(ActualProductPrice);
		    int ExpectedPrice=Utilities.convertStringToInt(ProductPrice);
		    report.log(Status.INFO, "Product Price In Product Page is: "+ExpectedPrice);
		    report.log(Status.INFO, "Product Price In Cart Page is: "+ActualPrice);
		    Assert.assertEquals(ActualPrice,ExpectedPrice);
		    report.log(Status.PASS, "Product Price Validation Success");
		
		
		
	}
	
	@Test(priority=4,dataProviderClass=Utilities.class,dataProvider="testdata")
	public void subTotalAmountValidation(String product1,String product2) throws InterruptedException {
		
			add_product_to_cart(product1);
			add_product_to_cart(product2);
		    home_page.goToCart(); 
		    int actual_total=cartPage.addPricesOfAllItems();
		    int subTotal=cartPage.getSubTotal();
		    
		    report.log(Status.INFO, "Addition of all items price total: "+actual_total);
		    report.log(Status.INFO, "Actual sub total: "+subTotal);
		    Assert.assertEquals(actual_total,subTotal);
		    report.log(Status.PASS, "Sub Total Price Validation Success");
		
		
		
	}

	
	public String add_product_to_cart(String productName) throws InterruptedException {
		home_page.searchProduct(productName);
		productSearchResultPage.clickOnFirstProductFromSearchResults();
		Utilities.changeWindowToChild(driver);
		String ProductTitle=productPage.getProductTitle();
		report.log(Status.INFO, "Product Title: "+ProductTitle);
		productPage.clickOnAddToCartButton();
        Thread.sleep(3000);
		productPage.closeAddCartSuccessMessage();
		Thread.sleep(3000);
		return ProductTitle;
	}
}
