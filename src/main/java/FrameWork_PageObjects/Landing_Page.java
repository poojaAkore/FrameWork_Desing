package FrameWork_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusability.AbstractComponant;

public class Landing_Page extends AbstractComponant {

	
	//loacalobject//currentclass driver
	WebDriver driver;
	
	//constructor
	public Landing_Page(WebDriver driver)
	{
		//Initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement Username;
	
	@FindBy(id="userPassword")
	WebElement Password;

	@FindBy(id="login")
	WebElement Button;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	//.ng-tns-c4-15.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	
public Productcatlog Login_Application(String email,String pass)
{
	Username.sendKeys(email);
	Password.sendKeys(pass);
	Button.submit();
	Productcatlog cat=new Productcatlog(driver);
	return cat;
}

public String getErrorMessage()
{
	waitForElementToAppear(errormessage);
     return errormessage.getText();	
}


public void goTo() {
	// TODO Auto-generated method stub
	driver.get("https://rahulshettyacademy.com/client");
}


}