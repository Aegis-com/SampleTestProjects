package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static ThreadLocal<WebDriver> tlDriver;

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("./src/test/java/config/config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static synchronized WebDriver getWebDriver() {
		if (tlDriver == null) {
			tlDriver = new ThreadLocal<>();
			String browser = prop.getProperty("browser");

			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver());
			} else if (browser.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver());
			} else if (browser.equals("edge")) {				
				WebDriverManager.edgedriver().setup();				
				EdgeOptions options = new EdgeOptions();
				options.addArguments("-inprivate");
				tlDriver.set(new EdgeDriver(options));
			} else if (browser.equals("safari")) {
				tlDriver.set(new SafariDriver());
			} else {
				System.out.println("Please pass the correct browser value: " + browser);
			}

			/*
			 * driver.manage().window().maximize(); driver.manage().deleteAllCookies();
			 * driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 * 
			 * driver.get(prop.getProperty("url"));
			 */

			tlDriver.get().manage().deleteAllCookies();
			tlDriver.get().manage().window().maximize();
			tlDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			tlDriver.get().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			tlDriver.get().get(prop.getProperty("url"));
		}
		return tlDriver.get();

	}
	
	
	public static void tearDownWebDriver() {
		
		tlDriver.get().close();
		System.out.println("Value for the Browser is : "+ tlDriver.get() );
		tlDriver = null;
		System.out.println("Value for the Browser is : "+ tlDriver );

		
	}
	
	public static void takeScreenshot(Scenario scenario) {
		 if (scenario.isFailed()) { // take screenshot: 
	            String screenshotName = scenario.getName().replaceAll(" ", "_");
	            byte[] sourcePath = ((TakesScreenshot) tlDriver.get()).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(sourcePath, "image/png", screenshotName);
	 
		 }
	}
	
	
	

//	public  WebDriver returnDriver() {
//		return getDriver();
//	}
//	
//	
//	public  synchronized WebDriver getDriver() {
//		return tlDriver.get();
//	}

}
