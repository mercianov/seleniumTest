package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pagefactory.Checkout_PF;

public class CheckoutDemoSteps_PF {
	public WebDriver driver ;
	Checkout_PF co;
	
	@Before
	public void prepTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		co = new Checkout_PF(driver);
	}

	@Given("^user has logged in$")
	public void user_is_on_login_website() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}

	@When("^user add to cart (.*) item$")
	public void add_item(String order) throws InterruptedException {
		Thread.sleep(1000);
		int n=0;
		if(order.contains("random")){
			co.addToCartRandom();
		} else {
			n = Integer.parseInt(order.replace(" fix", ""));
			co.addToCart(n);
		}
	}
	@And("^user click cart icon$")
	public void click_cart_icon() throws InterruptedException {
		Thread.sleep(1000);
		co.openCart();
	}
	@And("^user click checkout button$")
	public void checkout_button_in_cart_page() throws InterruptedException {
		Thread.sleep(1000);
		co.clickCheckout();
	}
	@And("^user fill in customer data$")
	public void fill_customer_data() throws InterruptedException {
		Thread.sleep(1000);
		co.fillCustomerData();
	}
	@And("^user click continue$")
	public void click_continue() throws InterruptedException {
		co.continueToFinish();
		Thread.sleep(1000);
	}
	@And("^user verify the checkout page$")
	public void verify_checkout_page_data() throws InterruptedException {
		co.verifyCheckoutData();
		Thread.sleep(1000);
	}
	@And("^user verify the checkout page random order$")
	public void verify_checkout_page_data_multi_order() throws InterruptedException {
		co.verifyCheckoutDataRandom();
		Thread.sleep(1000);
	}
	@And("^user click finish$")
	public void click_finish() throws InterruptedException {
		co.clickFinish();
		Thread.sleep(1000);
	}
	@Then("^user has success checkout$")
	public void success_checkout() throws InterruptedException {
		co.sucessCheckout();
		Thread.sleep(1000);
	}
	
	@After
	public void close() {
		driver.close();
		driver.quit();
	}
	
}