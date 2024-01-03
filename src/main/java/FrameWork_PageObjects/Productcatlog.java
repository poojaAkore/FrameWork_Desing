package FrameWork_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Reusability.AbstractComponant;

public class Productcatlog extends AbstractComponant {

	WebDriver driver;
	
	public Productcatlog(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//this is for webelemnt
	@FindBy(css=".mb-3")
	List<WebElement> products; 
	
	@FindBy(css=".ng-animating")
	WebElement  spinner;
	//
	By productby=By.cssSelector(".mb-3");//this line for getting the list of product
	By ADDTOCART=By.cssSelector(".card-body button:last-of-type");
	By Toastmessage=By.cssSelector("#toast-container");
	
	
	
	public List<WebElement> getproductList()
	{
		waitForElementToAppear(productby);
		return products;
	}
	
	public WebElement getProductName(String productName)
	{
		WebElement prod = getproductList().stream()
			    .filter(product -> product.findElement(By.xpath("//div[@class='card-body']//b")).getText().equals(productName))
			    .findFirst()
			    .orElse(null);
		return prod;
	}
	public void AddProductToCart(String productName)
	{
		WebElement prod=getProductName(productName);
		prod.findElement(ADDTOCART).click();
		waitForElementToAppear(Toastmessage);
		waitForElementDisAppear(spinner);
	}
	 
}
