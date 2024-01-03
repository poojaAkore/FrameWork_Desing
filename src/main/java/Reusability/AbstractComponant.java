package Reusability;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameWork_PageObjects.Cartpage;
import FrameWork_PageObjects.OrderHistory;

public class AbstractComponant {
	
	WebDriver driver;
	
	public AbstractComponant(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy (css="[routerlink*='/dashboard/cart']")
	WebElement cart;
	
	@FindBy (css="[routerlink*='/dashboard/myorders']")
	WebElement orders;
	
	public Cartpage clickonCart()
	{
		cart.click();
		Cartpage cartp=new Cartpage(driver);
		return cartp;
	}
	
	public OrderHistory gotoOrderHistory()
	{
		orders.click();
		OrderHistory order=new OrderHistory(driver);
		return order;
	}
	

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	public void waitForElementDisAppear(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void Scrolldown()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
	}
}
