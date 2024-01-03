package FrameWork_TestClasses.testcomponant;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Extent_ReporterNG;

public class Listeners<extent, test> extends Base_Test implements ITestListener{
	
     ExtentTest  test;
	ExtentReports extent =Extent_ReporterNG.getObjects();
	
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS,"Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		test.fail(result.getThrowable());
	try {
		driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	}catch(Exception e1){
		e1.printStackTrace();
		
	}
		
		//scrrenshot
		
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (filepath != null) {
		test.addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
		}
	}
	public String getScreenshot(String methodName, WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
		 if (driver == null) {
		        // Handle the case where the WebDriver is null
		        System.out.println("WebDriver is null. Cannot capture screenshot.");
		        return null;
		    }
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File filepath=new File(System.getProperty("user.dir")+ "//ScreenShot//" + methodName + ".png");
		FileUtils.copyFile(source, filepath);
		return System.getProperty("user.dir")+ "//reports//" + methodName + ".png";
	}
	

	

	}


