package pagefactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Checkout_PF {


	WebDriver driver;
	int index;
	String firstName;
	String lastName;
	String postalCode;
	String itemName;
	String itemDesc;
	String itemPrice;
	double totalWithoutTax = 0;
	double totalWithTax = 0;
	
	public Checkout_PF(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void addToCart(int jumlahItemFix) throws InterruptedException {
		for(int i=1; i<=jumlahItemFix;i++) {
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button [contains (@id, 'add-to-cart')])["+i+"]")).click();
		}
	}
	public void addToCartRandom() {
		Random rn;
		rn = new Random();
		index = rn.nextInt(6) + 1;
		itemName = driver.findElement(By.xpath("(//div[@class='inventory_item_name '])["+index+"]")).getText();
		itemDesc = driver.findElement(By.xpath("(//div[@class='inventory_item_desc'])["+index+"]")).getText();
		itemPrice = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])["+index+"]")).getText();
		driver.findElement(By.xpath("(//button [contains (@id, 'add-to-cart')])["+index+"]")).click();

	}
	
	public void randomProducts(int n) throws InterruptedException {
        int[] numbers = generateRandomNonDuplicateNumbers(n, 1, 6);
        
		for (int i=0; i<numbers.length;i++) {
			driver.findElement(By.xpath("(//button [contains (@id, 'add-to-cart')])["+numbers[i]+"]")).click();
			Thread.sleep(1000);
		}
	}
	
	private static int[] generateRandomNonDuplicateNumbers(int n, int min, int max) {
		if (n > (max - min + 1)) {
            throw new IllegalArgumentException("Cannot generate more unique numbers than the range allows.");
        }

        int[] result = new int[n];
        for (int i = min; i <= max; i++) {
        	System.out.print("i "+i);
        	System.out.print("min "+min);
            result[i - min] = i;
        }

        Random random = new Random();
        for (int i = n - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Swap numbers[i] and numbers[index]
            int temp = result[i];
            result[i] = result[index];
            result[index] = temp;
        }

        return Arrays.copyOf(result, n);
	}
	
	public void openCart() {
		driver.findElement(By.className("shopping_cart_badge")).click();	
	}

	public void clickCheckout() {
		driver.findElement(By.id("checkout")).click();
		
	}

	public void fillCustomerData() throws InterruptedException {
		firstName="Lola";
		lastName="Dilup";
		postalCode="8897";
		driver.findElement(By.id("first-name")).sendKeys(firstName);
		Thread.sleep(1000);
		driver.findElement(By.id("last-name")).sendKeys(lastName);
		Thread.sleep(1000);
		driver.findElement(By.id("postal-code")).sendKeys(postalCode);
		Thread.sleep(1000);
		
	}

	public void continueToFinish() {
		driver.findElement(By.id("continue")).click();
		
	}

	public void verifyCheckoutData() {
		assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Payment Information']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Shipping Information']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Price Total']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[@class='summary_tax_label']")).isDisplayed());
		totalPrice();	
		totalWithTax();
	}
	
	public void verifyCheckoutDataRandom() {
		assertEquals(itemName,driver.findElement(By.className("inventory_item_name")).getText());
		assertEquals(itemDesc,driver.findElement(By.className("inventory_item_desc")).getText());
		assertEquals(itemPrice,driver.findElement(By.className("inventory_item_price")).getText());
		assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Payment Information']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Shipping Information']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Price Total']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[@class='summary_tax_label']")).isDisplayed());
		totalPrice();
		totalWithTax();
	}
	
	private void totalPrice() {
		List<WebElement> l = driver.findElements(By.className("inventory_item_price"));
		for(int i=0; i<l.size(); i++) {
			totalWithoutTax+= Double.valueOf(l.get(i).getText().replace("$", ""));
			
		}
		String actual = driver.findElement(By.className("summary_subtotal_label")).getText();
		String expected = "Item total: $"+totalWithoutTax;

		assertEquals(expected,actual);
		
	}
	
	private void totalWithTax() {
		String stringTax = driver.findElement(By.className("summary_tax_label")).getText();
		double tax = Double.valueOf(stringTax.replace("Tax: $", ""));
		totalWithTax=tax+totalWithoutTax;
		String expected="Total: $" + String.format("%.2f", totalWithTax);
		System.out.println("tax = "+stringTax);
		System.out.println("ekspektasi = "+expected);
		assertEquals(expected, driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']")).getText());
	}

	public void clickFinish() {
		driver.findElement(By.id("finish")).click();
		
	}

	public void sucessCheckout() {
		assertTrue(driver.findElement(By.xpath("//img[@alt='Pony Express']")).isDisplayed());
		assertEquals("Thank you for your order!",driver.findElement(By.className("complete-header")).getText());
		assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!",driver.findElement(By.className("complete-text")).getText());
		assertTrue(driver.findElement(By.id("back-to-products")).isDisplayed());
	}
}
