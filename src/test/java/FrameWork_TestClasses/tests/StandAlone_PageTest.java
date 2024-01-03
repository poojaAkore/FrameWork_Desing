package FrameWork_TestClasses.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import FrameWork_PageObjects.Landing_Page;

public class StandAlone_PageTest {

	public static void main(String[] args) throws InterruptedException
	{
		String productName="ZARA COAT 3";
	System.setProperty("webdriver.chrome.driver","C:\\Users\\Ajinkya\\Desktop\\Driver\\chromedriver-win32\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.get("https://rahulshettyacademy.com/client");
	//Find WebElement
	driver.findElement(By.id("userEmail")).sendKeys("patwaripooja4@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Pooja@123");
	driver.findElement(By.id("login")).click();
	
	//Find the list of products
	List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	
	//Itrate the product & select Particular product
	WebElement prod = products.stream()
		    .filter(product -> product.findElement(By.xpath("//div[@class='card-body']//b")).getText().equals(productName))
		    .findFirst()
		    .orElse(null);

	
	//click on add product button
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	//Wait
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	//Click on cart
	driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
	
	//verify items selected in cart or not
	List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	boolean match = cartProducts.stream()
		    .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

	Assert.assertTrue(match);

	//click on checkout button
	driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
	//using action class for perform the actions
	
	Actions act = new Actions(driver);
	WebElement countryField = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
	act.sendKeys(countryField, "India").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

	//Click on india
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	Thread.sleep(3000);
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,500)");
	//click on submit order
	
	driver.findElement(By.xpath("//div[@class='actions']/a")).click();
	//wait.until(ExpectedConditions . elementToBeClickable (By.xpath("//a[@class='btnn action__submit ng-star-inserted']")));
	Thread.sleep(5000);
	//verify Thankyou message
	String ConfirmMessage=driver.findElement(By.xpath("//h1[text()=' Thankyou for the order. ']")).getText();
	Thread.sleep(2000);
	Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
	driver.close();
	
	

}}
