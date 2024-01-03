 package FrameWork_TestClasses.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameWork_PageObjects.Cartpage;
import FrameWork_PageObjects.CheckoutPage;
import FrameWork_PageObjects.ConfirmationPage;
import FrameWork_PageObjects.Landing_Page;
import FrameWork_PageObjects.Productcatlog;
import FrameWork_TestClasses.testcomponant.Base_Test;

public class SubmitOrder extends Base_Test{
	
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData", groups="Purchase")
	
	public void submitOrder(String email,String password,String productName) throws InterruptedException, IOException
	{
		
		    
			
			Productcatlog cat=page.Login_Application(email, password);
			//findout the list of products
            List<WebElement>products=cat.getproductList();
			
			//Itrate the product & select Particular product
			//click on add product button
			cat.getProductName(productName);
			cat.AddProductToCart(productName);
			//ClickOn Cart &verify Selected item
			
			Cartpage cp=new Cartpage(driver);
			cp.clickonCart();
			List<WebElement>cartProductList=cp.getcartproductList();
			boolean match=cp.verifyproductName("productName");
			Assert.assertTrue(match);
			cp.clickOnCheckoutButton();
			
			CheckoutPage checkout=new CheckoutPage(driver);
			Thread.sleep(2000);
			checkout.SelectCountry("india");
			checkout.Scrolldown();
			
			ConfirmationPage confirm=checkout.submitOrder();
			String ConfirmMessage=confirm.getConfirmationmsg();
			Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
	
	}
	

	@Test(dependsOnMethods= {"submitOrder"})
	
	public void OrderhistoryTest()
	{
		Productcatlog cat=page.Login_Application("patwaripooja4@gmail.com", "Pooja@123");
		FrameWork_PageObjects.OrderHistory order=cat.gotoOrderHistory();

		Assert.assertTrue(order.verifyOrderDisplay(productName));
	}
	
	
	

			
@DataProvider
	
	public Object[][] getData()
	{
		return new Object[][] {{"patwaripooja4@gmail.com", "Pooja@123","ZARA COAT 3"},{"poojakore253@gmail.com","Pooja@123","ADIDAS ORIGINAL"}};
	}
	 
}
	

		
	


