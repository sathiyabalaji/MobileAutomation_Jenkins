package com.mobileAuto;

//package TestNG;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;




public class ExtentListeners implements ITestListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	

	public void onTestStart(ITestResult result) {

	
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName().replaceAll("_"," "));
        testReport.set(test);
        

	}

	public void onTestSuccess(ITestResult result) {

		//
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) Common.driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 System.out.println("User.dir"+System.getProperty("user.dir"));
		 String destination = System.getProperty("user.dir") + "\\PassedTestsScreenshots\\" + result.getMethod().getMethodName()+"-"+dateName+".png";
		 
		 
		 File finalDestination = new File(destination);
		 try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
				testReport.get().pass("<details>" + "<summary>" + "<b>" + "<font color=" + "green>" + "PASSED"
				+ "</font>" + "</b >" + "</summary>" 						+ "</details>"+" \n"
						+ "<b>Screenshot <a hre="+System.getProperty("user.dir") + 
						"\\PassedTestsScreenshots\\" + result.getMethod().getMethodName()
						+"-"+dateName+".png"+">"
					
						+ "<img width=\"200\" height=\"200\" src="+System.getProperty("user.dir") + 
						"\\PassedTestsScreenshots\\" + result.getMethod().getMethodName()
						+"-"+dateName+".png");
		
		System.out.println("Image"+"<img width=\"200\" height=\"200\" src="+System.getProperty("user.dir") + 
						"\\PassedTestsScreenshots\\" + result.getMethod().getMethodName()
						+"-"+dateName+".png"+"</b>"+" \n");
		
		
		//	
		
		
		String methodName=result.getMethod().getMethodName().replaceAll("_"," ");
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		

	}

	public void onTestFailure(ITestResult result) {


		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) Common.driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		// String destination = "./FailedTestsScreenshots/"+excepionMessage+"-"+dateName+".png";
		 System.out.println("User.dir"+System.getProperty("user.dir"));
		// System.out.println(System.getProperty("user.dir") + "\\FailedTestsScreenshots\\" + excepionMessage+"-"+dateName+".png");
		 String destination = System.getProperty("user.dir") + "\\FailedTestsScreenshots\\" + result.getMethod().getMethodName()+"-"+dateName+".png";
		 
		 
		 File finalDestination = new File(destination);
		 try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		 
		
		
		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+""
						+ "</details>"+" \n"
						+ "<b>Screenshot <a href="+System.getProperty("user.dir") + 
						"\\FailedTestsScreenshots\\" + result.getMethod().getMethodName()
						+"-"+dateName+".png target=\"_blank\"+"+">"
					
						+ "<img width=\"200\" height=\"200\" src="+System.getProperty("user.dir") + 
						"\\FailedTestsScreenshots\\" + result.getMethod().getMethodName()
						+"-"+dateName+".png></img></a>");
		
		System.out.println("Image"+"<img width=\"200\" height=\"200\" src="+System.getProperty("user.dir") + 
						"\\FailedTestsScreenshots\\" + result.getMethod().getMethodName()
						+"-"+dateName+".png"+"</b>"+" \n");
		
		
		/*try {

			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
							.build());
		} catch (IOException e) {

		}*/
		
		String methodName=result.getMethod().getMethodName().replaceAll("_"," ");
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " FAILED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.RED);
		testReport.get().fail(m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName().replaceAll("_"," ");
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

}
