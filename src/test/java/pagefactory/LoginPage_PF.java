package pagefactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_PF {
	@FindBy(xpath="//input[@id='user-name']")
	WebElement txt_username;
	@FindBy(xpath="//input[@id='password']")
	WebElement txt_password;
	@FindBy(xpath="//input[@id='login-button']")
	WebElement login_button;
	@FindBy(xpath="//div[@class='login_logo']")
	WebElement titleLoginPage;
	
	String username;
	String password;
	WebDriver driver;
	
	public LoginPage_PF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void isOnLoginWebsite() {
		assertTrue(titleLoginPage.isDisplayed());
		assertTrue(txt_username.isDisplayed());
		assertTrue(txt_password.isDisplayed());
		assertTrue(login_button.isDisplayed());
	}

	public void enterUsername(String type_username) {
		if(type_username.equals("correct")) {
			username = "standard_user";
		} else if(type_username.equals("locked")) {
			username = "locked_out_user";
		} else if(type_username.equals("problem")) {
			username = "problem_user";
		}else if(type_username.equals("glitch")) {
			username = "performance_glitch_user";
		}else if(type_username.equals("error")) {
			username = "error_user";
		}else if(type_username.equals("visual")) {
			username = "visual_user";
		}else if(type_username.contains("blank")) {
			username = "";
		}
		txt_username.sendKeys(username);
	}
	
	public void enterPassword(String type_password) {
		if(type_password.equals("correct")) {
			password="secret_sauce";
		}else if(type_password.equals("wrong")) {
			password="wrong";
		}else if(type_password.equals("blank")) {
			password="";
		}
		txt_password.sendKeys(password);
	}
	
	public void clickLoginButton() {
		login_button.click();
	}

	public void successLogin() {
		assertEquals("Products",driver.findElement(By.xpath("//span[@class='title']")).getText());
	}

	public void failLogin(String type_error) {
		if(type_error.equals("wrong password")) {
			assertEquals("Epic sadface: Username and password do not match any user in this service",driver.findElement(By.xpath("//h3[@data-test='error']")).getText());
		} else if (type_error.equals("blank password")) {
			assertEquals("Epic sadface: Password is required",driver.findElement(By.xpath("//h3[@data-test='error']")).getText());		
		}else if (type_error.equals("blank username")) {
			assertEquals("Epic sadface: Username is required",driver.findElement(By.xpath("//h3[@data-test='error']")).getText());		
		}
		
	}

	public void onWebsiteProductList() {
		assertTrue(driver.findElement(By.xpath("//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[1]/div[1]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).isDisplayed());
	
		
	}
}
