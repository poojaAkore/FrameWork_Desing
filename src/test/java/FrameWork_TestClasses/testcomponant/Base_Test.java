package FrameWork_TestClasses.testcomponant;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import FrameWork_PageObjects.Landing_Page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test {
	
	public WebDriver driver;
	public Landing_Page page;
	
	public WebDriver InitializeDriver() throws IOException
	{
	
		//properties class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			   
			
	    }
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String TestcaseName, WebDriver driver) throws IOException
	{
		 if (driver == null) {
		        // Handle the case where the WebDriver is null
		        System.out.println("WebDriver is null. Cannot capture screenshot.");
		        return null;
		    }
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File filepath=new File(System.getProperty("user.dir")+ "//reports//" + TestcaseName + ".png");
		FileUtils.copyFile(source, filepath);
		return System.getProperty("user.dir")+ "//reports//" + TestcaseName + ".png";
		
	}
	
		@BeforeMethod(alwaysRun=true)
		public Landing_Page launchApplication() throws IOException
		{
		driver = InitializeDriver();	
	     page=new Landing_Page(driver);
		page.goTo();
		return page;
		}
		
		@AfterMethod(alwaysRun=true)
		public void tearDown()
		{
			driver.close();
		}
				
				
				
				
}	
				
				
				
				
				
				
				
				
				
				
				
				
				
			
	


