package FrameWork_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusability.AbstractComponant;

public class Cartpage extends AbstractComponant {
	
	WebDriver driver;
	
	public Cartpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement>cartproducts;
	
	@FindBy (xpath="//li[@class='totalRow']//button")
	WebElement CheckoutButton;
	
	By cartProduct=By.cssSelector(".cartSection h3");
	
	
	
	
	public List<WebElement> getcartproductList()
	{
		
		return cartproducts;
	}
	
	public boolean verifyproductName(String productName)
	{
		
		boolean match =getcartproductList().stream()
			    .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return true;
	}
	public CheckoutPage clickOnCheckoutButton()
	{
		CheckoutButton.click();
		return new CheckoutPage(driver);
	}

}
