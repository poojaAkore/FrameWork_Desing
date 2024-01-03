package FrameWork_TestClasses.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameWork_PageObjects.Cartpage;
import FrameWork_PageObjects.CheckoutPage;
import FrameWork_PageObjects.ConfirmationPage;
import FrameWork_PageObjects.Landing_Page;
import FrameWork_PageObjects.Productcatlog;
import FrameWork_TestClasses.testcomponant.Base_Test;

public class ErrorValidation extends Base_Test{

	@Test(groups="Errorhandling")
	
	public void loginErrorValidation() throws InterruptedException, IOException
	{
		
		    String productName="ZARA COAT 3";
			Productcatlog cat=page.Login_Application("patwaripooja4@gmail.com", "Poo1ja@123");
			Assert.assertEquals("Incorrect email or password",page.getErrorMessage());
			

			
	}
	@Test
	public void producterrorvalidation() throws InterruptedException, IOException
	{
		
		    String productName="ZARA COAT 3";
			
			Productcatlog cat=page.Login_Application("poojakore253@gmail.com", "Pooja@123");
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
			boolean match=cp.verifyproductName("ZARA COAT 33");
			Assert.assertFalse(match);
		
			
			

			

			
			
			
			

		
	}

			
			
			
		
	}


