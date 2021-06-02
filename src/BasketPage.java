import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class BasketPage {
    WebDriver driver;
	
	By startBuyingLink = By.xpath("/html/body/main/header/div[1]/section/div[2]/a");
	By basketPopup = By.id("cartContainer");
	By totalBasketPrice = By.xpath ("/html/body/main/aside/div/div/footer/div[2]/div/div/div[1]/span");
	
	
	public BasketPage(WebDriver driver) {		
			this.driver = driver;		
		}
	    
    public void startBuying() {
    	driver.findElement(startBuyingLink).click();
    	driver.findElement(basketPopup).isDisplayed();
   
    }
    
    public void checkTotalBasketSum(double price) {
    	double shippingPrice = 30.00;    	
    	
    	String split[] = driver.findElement(totalBasketPrice).getText().split("\\n");
    	double totalSum = Double.parseDouble(split[split.length-1]);
    	
    	Assert.assertTrue("Verification Failed: Total basket price is wrong",Math.abs(totalSum - (shippingPrice + price )) < 0.000001d );
    
    }

}
