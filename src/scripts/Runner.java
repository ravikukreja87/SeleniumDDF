package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import utils.ReadWrite;

public class Runner {
	static WebDriver driver;

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static void main(String[] args) {
		ReadWrite rw = new ReadWrite();
		rw.readExcel();
		System.out.println(rw.username);
		System.out.println(rw.password);
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		String baseURL = "http://hms.techcanvass.co.in/";
		String userName = null;
		String password = null;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		for (int i = 0; i < 2; i++) {
			driver.get(baseURL);
			userName = rw.username.get(i);
			password = rw.password.get(i);
			driver.findElement(By.id("txtUserName")).sendKeys(userName);
			driver.findElement(By.id("txtPassword")).sendKeys(password);
			driver.findElement(By.id("LoginButton")).click();
			if (isAlertPresent()) {
				String errorMessage = driver.switchTo().alert().getText();
				driver.quit();
				Assert.fail("Login Failed. Message returned = " + errorMessage);
			}
			WebElement label = driver.findElement(By.id("ctl00_lblloginuser"));
			String actualUserName = label.getText();
			label.getClass();
			label.click();
			Assert.assertEquals("Pradnya", actualUserName, "Failed -- Values are not equal");
		}
		driver.quit();
	}
}