import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SearchPage {
    WebDriver driver;
	
	By searchBtn = By.xpath("/html/body/main/header/div/div[3]/form/fieldset/div/button");
	By searchInput = By.id("js-site-search-input");
	By searchedList = By.xpath("//*[@id=\"mainProductGrid\"]/li");
	By sortList = By.id("sortOptions1");
	By milkEggsCheckbox = By.xpath("//*[text()='חלב וביצים']");
	By firstItemInListAddLink = By.xpath("/html/body/main/div[2]/div[2]/div[2]/ul/li[1]/section/ul/li[1]/div[1]/div[4]/button[1]");
	By firstItemInList = By.xpath("/html/body/main/div[2]/div[2]/div[2]/ul/li[1]/section/ul/li[1]/div[1]");
	By cheapestPrice = By.xpath("/html/body/main/div[2]/div[2]/div[2]/ul/li[1]/section/ul/li[1]/div[1]/div[3]/div/div[1]/span/span[1]");
	
	public SearchPage(WebDriver driver) {		
			this.driver = driver;		
		}
	    
    public void searchForAnItem(String name) {
    	driver.findElement(searchInput).sendKeys(name);
    	driver.findElement(searchBtn).click();
    	
    	List<WebElement> searchedItems =driver.findElements(searchedList);
    	Assert.assertTrue("Verification Failed: Search didn't give any results", searchedItems.size() >1 );     	
   
    }
    
    public void sortItems(String sortOrder) throws InterruptedException {
    	Select dropdown = new Select(driver.findElement(sortList));
    	dropdown.selectByValue(sortOrder);    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	wait.until(ExpectedConditions.elementToBeClickable(milkEggsCheckbox));		
    	driver.findElement(milkEggsCheckbox).click();
    }
    
    public double addItemToTheBasket() throws InterruptedException {
    	double price= 0;
   
    	
    	Actions action = new Actions(driver);
    	action.moveToElement(driver.findElement(firstItemInList)).build().perform();    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	wait.until(ExpectedConditions.elementToBeClickable(firstItemInListAddLink)); 
    	price = Double.parseDouble(driver.findElement(cheapestPrice).getText());
    	driver.findElement(firstItemInListAddLink).click();
    	Thread.sleep(1000);
    	
    	return price;
    }

}
