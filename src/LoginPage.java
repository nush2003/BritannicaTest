import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class LoginPage {
    WebDriver driver;
	
	By loginLink = By.xpath("/html/body/main/header/div[1]/section/div[2]/div/div[2]/ul/li[1]/button");
	By emailField = By.id("j_username");
	By passField = By.id("j_password");
	By enterBtn = By.xpath("/html/body/main/header/div[1]/section/div[2]/div/div[2]/ul/li[1]/div/div[2]/div/div[2]/form/div[3]/button");
	By accountName = By.xpath ("/html/body/main/header/div[1]/section/div[2]/div/div[1]/div[1]/a/strong");
		
	
    public LoginPage(WebDriver driver) {		
		this.driver = driver;		
	}
    
    public void login(String email, String pass) {
    	driver.findElement(loginLink).click();
    	driver.findElement(emailField).sendKeys(email);
    	driver.findElement(passField).sendKeys(pass);
    	driver.findElement(enterBtn).click();    	
    }
    
    public void checkLoginAccountName(String name) {
    	String text = driver.findElement(accountName).getText();
    	
    	Assert.assertTrue("Verification Failed: Wrong user name after login", text.contains(name)); 
    	
    	
    }
    

}
