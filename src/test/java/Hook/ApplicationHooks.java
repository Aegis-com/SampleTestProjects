package Hook;



import org.openqa.selenium.WebDriver;
import Util.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;




public class ApplicationHooks extends BaseClass {
	

	public WebDriver driver;
	public Scenario scenario;
	
	public BaseClass testBaseObj;
	@Before(order = 1)
	public void setUp() {
		System.out.println("SetUp Steps to launch Browser and Url");
		BaseClass.getWebDriver();	
	}
	
	@Before(order = 0)
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	}
	
	@After(order = 0)
	public void tearDown() {
		System.out.println("Tear Down Steps to Close the  browser steps");
		BaseClass.tearDownWebDriver();
		
	}
	
	@After(order = 1)
    public void failedScreenShots() {
		System.out.println("Taking Screenshot of Failed Scenarios");
		BaseClass.takeScreenshot(scenario);

        }
	

}
