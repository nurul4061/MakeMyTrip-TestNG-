package makeMyTrip;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.customExpectedCondition;

public class validateAutoSuggestion {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		
		// For Chrome
		// System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();

		// For Firefox
		System.setProperty("webdriver.gecko.driver", "C:\\work\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 20);
	}

	@Test
	public void validateSearch() {

		driver.get("https://www.makemytrip.com");
		driver.manage().window().maximize();

		driver.findElement(By.cssSelector("input[id=\"fromCity\"]")).click();
		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys("Del");
		wait.until(new customExpectedCondition("SUGGESTIONS", "//*[@id='react-autowhatever-1']/div/div/p"));

		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys(Keys.DOWN);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String query = "return document.querySelector(\"input[placeholder='From']\").value;";
		String text = (String) js.executeScript(query);
		System.out.println(text);

		int i = 0;
	
		while (!text.equalsIgnoreCase("Delta Junction")) {
			i++;
			driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys(Keys.DOWN);

			text = (String) js.executeScript(query);
			System.out.println(text);
			if (i > 10) {
				break;
			}

		}

		if (i > 10) {
			System.out.println("Element not found");
		} else {
			System.out.println("Delta Junction validated successfully");
			driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys(Keys.ENTER);
		}
	}

	@AfterClass
	public void tearDown() {
		//driver.quit();
	}
}
