package StepDefinitions;

import java.time.Duration;


import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pagefactory.Checkout_PF;
import pagefactory.LoginPage_PF;
import pagefactory.controller;


public class LoginDemoSteps_PF {
	public WebDriver driver = null;
	LoginPage_PF login;
	controller crl;
	
	@Before
	public void prepTest() {
		crl = new controller(driver);
		login = new LoginPage_PF(crl.driver);
	}

	@Given("^user is on website login page$")
	public void user_is_on_login_website() throws InterruptedException {
		Thread.sleep(1000);
		login.isOnLoginWebsite();
	}
	
	@When("^user enter (.*) username and (.*) password$")
	public void user_enter_username_and_password(String type_username, String type_password) throws InterruptedException {
		login = new LoginPage_PF(driver);
		Thread.sleep(1000);
		login.enterUsername(type_username);
		Thread.sleep(1000);
		login.enterPassword(type_password);
	}

	@And("^click login button$")
	public void click_on_login_button() throws InterruptedException {
		Thread.sleep(1000);
		login.clickLoginButton();
	}
	
	@Then("^user successfully login$")
	public void user_successfully_login() throws InterruptedException {
		Thread.sleep(1000);
		login.successLogin();
	}
	
	@Then("^user get error (.*) on login page$")
	public void get_error_login(String type_error) throws InterruptedException {
		Thread.sleep(1000);
		login.failLogin(type_error);
		
	}

	@Then("user is one website item product list")
	public void user_is_one_website_item_product_list() throws InterruptedException {
		Thread.sleep(1000);
		login.onWebsiteProductList();
	}


	public void close() {
		driver.close();
		driver.quit();
	}
	

	
}
