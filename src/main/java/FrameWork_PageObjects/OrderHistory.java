package FrameWork_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusability.AbstractComponant;

public class OrderHistory extends AbstractComponant {
	
	WebDriver driver;
	
	public OrderHistory(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement>productNames;
	
	@FindBy (xpath="//li[@class='totalRow']//button")
	WebElement CheckoutButton;
	

	
	
	
	
	
	
	
	public boolean verifyOrderDisplay(String productName)
	{
		
		boolean match =productNames.stream()
			    .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return true;
	}
	public CheckoutPage clickOnCheckoutButton()
	{
		CheckoutButton.click();
		return new CheckoutPage(driver);
	}

}
