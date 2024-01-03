package FrameWork_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusability.AbstractComponant;

public class ConfirmationPage extends AbstractComponant {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath="//h1[@class='hero-primary']")
	WebElement Confirmationmessage;
	
	public String getConfirmationmsg()
	{
		return Confirmationmessage.getText();
	}
	
	
	
	

}
