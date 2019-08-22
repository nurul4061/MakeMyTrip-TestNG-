package makeMyTrip;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.customExpectedCondition;

public class flightBooking {
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
	public void bookingFlight() {

		driver.get("https://www.makemytrip.com");

		driver.manage().window().maximize();

		driver.findElement(By.cssSelector("input[id=\"fromCity\"]")).click();

		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys("Del");

		// customize ExpectedCondition
		// because there is no unique identifier bedore and after typing in textBox of
		// dropdown
		wait.until(new customExpectedCondition("SUGGESTIONS", "//*[@id='react-autowhatever-1']/div/div/p")); //

		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys(Keys.ENTER);
		// System.out.println(driver.findElement(By.cssSelector("input[id=\"fromCity\"]")).getText());

		driver.findElement(By.cssSelector("input[placeholder='To']")).sendKeys("Kolk");
		wait.until(new customExpectedCondition("SUGGESTIONS", "//*[@id='react-autowhatever-1']/div[1]/div/p"));

		driver.findElement(By.cssSelector("input[placeholder='To']")).sendKeys(Keys.ARROW_DOWN);
		System.out.println(driver.findElement(By.cssSelector("input[placeholder='To']")).getText());
		driver.findElement(By.cssSelector("input[placeholder='To']")).sendKeys(Keys.ENTER);

		driver.findElement(By.className("DayPicker-Day--today")).click(); // click in Calender todays date

		driver.findElement(By.cssSelector("a.widgetSearchBtn")).click(); // click search button

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("fli_list_item0"))));

		// click in first search result
		driver.findElement(By.xpath("//*[@id=\"fli_list_item0\"]/div[1]/div[1]/div/div/div[4]/button")).click();

	}
	
	@AfterClass
	public void tearDown() {
		//driver.quit();
	}
}
