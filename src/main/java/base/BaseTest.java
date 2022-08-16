package base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.ProductSearchResultPage;
import utilities.Utilities;

public class BaseTest extends ObjectsRepo {
	
	
	String browser=Utilities.read_propert_value("BROWSER");
	String ApplicationURL=Utilities.read_propert_value("URL");
	
	
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void setup() {
		
		
		if(browser.contentEquals("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(options);
			
		}
		
		else if(browser.contentEquals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
			
		}
		else if(browser.contentEquals("ie")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		
		driver.get(ApplicationURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		logger.info("application opened in "+browser);
		
		//Page Objects Initialization
		home_page=new HomePage();
		login_page=new LoginPage();
		productSearchResultPage=new ProductSearchResultPage();
		productPage=new ProductPage();
		cartPage=new CartPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		logger.info("Closing all opened browsers");
	}

}
