
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class mainTest {
	static WebDriver driver;
	
	LoginPage login = new LoginPage(driver);
	SearchPage search = new SearchPage(driver);
	BasketPage basket = new BasketPage(driver);

	
	
	
	@BeforeClass
	public static void initDriver() {
		
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");		
		ChromeOptions options = new ChromeOptions();		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	
	 @Test
	 public void test() throws IOException, InterruptedException {
				
		driver.get("https://www.shufersal.co.il");	
	    login.login("innar@britannica-ks.com", "0547630768");
	    login.checkLoginAccountName("אינה");
	    
	    basket.startBuying();
	    search.searchForAnItem("חלב");
	    search.sortItems("pricePerUnit-asc");
	    double cheapestPrice = search.addItemToTheBasket();
	    
	    basket.checkTotalBasketSum(cheapestPrice);					 
	 } 
	 
	
	
	 
	 @AfterClass
	 public static void tearDown() {
		driver.quit();
	 }
	
}
