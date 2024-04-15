package apr5;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class CrossBrowserTesting {
	WebDriver driver;
	@Parameters({"Browser"})
	@BeforeTest
	public void setUp(String brw)
	{
	switch (brw.toUpperCase()){
	case "CHROME":
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		break;
	case "INTERNTEXPLORER":
		driver = new InternetExplorerDriver();
		break;
	default:
		Reporter.log("browser value is not matching",true);
		break;
	}	
	}
	@DataProvider
	public Object supplyData()
	{
		Object login [][]= {{"Admin","Qedge123!@#"},{"Admin","Qedge123!@#"},{"Admin","Qedge123!@#"},
				{"Admin","Qedge123!@#"},{"Admin","Qedge123!@#"}};
		return login;
	}
	@Test(dataProvider = "supplyData")
	public void verify_Login(String user,String pass)
	{
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name("txtUsername")).sendKeys(user);
		driver.findElement(By.name("txtPassword")).sendKeys(pass);
		driver.findElement(By.name("Submit")).click();
		String Expected = "dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login Success::"+Expected+"     "+Actual);
		}
		else
		{
			Reporter.log("Login fail::"+Expected+"       "+Actual);
		}
		@AfterTest
		public void tearDown()
		{
			driver.quit();
		}

}
