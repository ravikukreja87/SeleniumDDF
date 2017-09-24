package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestNGDemo {
	WebDriver driver;
	String baseURL = "http://hms.techcanvass.co.in/";

	@DataProvider(name = "Credentials")
	public static Object[][] credentials() {
		return new Object[][] { { "Pradnya", "1994" }, { "inc", "Test@123" } };
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		// Initialize WebDriver Instance (driver)
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "Credentials")
	public void login(String userName, String password) {
		driver.get(baseURL);
		driver.findElement(By.id("txtUserName")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("LoginButton")).click();
		WebElement label = driver.findElement(By.id("ctl00_lblloginuser"));
		String actualUserName = label.getText();
		label.getClass();
		label.click();
		Assert.assertEquals("Pradnya", actualUserName, "Failed -- Values are not equal");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}