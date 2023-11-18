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
import pagefactory.UnitPage_PF;

public class UnitSteps_PF {
	public WebDriver driver ;
	UnitPage_PF up;
	
	
	public void prepTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		up = new UnitPage_PF(driver);
	}

	@Given("^user is on website to upload file$")
	public void go_to_web() {
		driver.get("https://the-internet.herokuapp.com/upload");
	}

	@When("^user choose file$")
	public void choose_file() throws InterruptedException {
		up.chooseFile();
	}
	@And("^user click upload button$")
	public void upload_file() throws InterruptedException {
		up.uploadFile();
	}

	@Then("^user get success upload message$")
	public void success_upload_message() throws InterruptedException {
		up.getMessage();
	}
	
	
	public void close() {
		driver.close();
		driver.quit();
	}
	
}