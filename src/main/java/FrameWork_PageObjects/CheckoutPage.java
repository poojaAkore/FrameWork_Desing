package FrameWork_PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Reusability.AbstractComponant;

public class CheckoutPage extends AbstractComponant {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy (css="[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy (xpath="//section[@class='ta-results list-group ng-star-inserted']//button[2]//span")
	WebElement SelectCountryField;
	
	@FindBy (xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement Submitbutton;
	
	@FindBy (xpath="//div[@class='actions']/a")
	WebElement Submitorder;
	
	By results=By.cssSelector(".ta-results");
	
	public void SelectCountry(String countryName)
	{
		Actions act = new Actions(driver);
		act.sendKeys(Country,countryName).build().perform();
		waitForElementToAppear(results);
		SelectCountryField.click();
	}
	//public void submitbutton()
	//{
	//	Submitorder.click();
	//}
	public ConfirmationPage submitOrder()
	{
		Submitorder.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
}
