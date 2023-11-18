package pagefactory;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UnitPage_PF {
	WebDriver driver;
	
	public UnitPage_PF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public void chooseFile() throws InterruptedException {
		driver.findElement(By.id("file-upload")).sendKeys("/Users/mercia/Downloads/data.csv");
	}


	public void uploadFile() throws InterruptedException {
		driver.findElement(By.id("file-submit")).click();
	}


	public void getMessage() throws InterruptedException {
		assertTrue(driver.findElement(By.xpath("//h3[normalize-space()='File Uploaded!']")).isDisplayed());
		assertTrue(driver.findElement(By.id("uploaded-files")).isDisplayed());
	}

}
