package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class customExpectedCondition implements ExpectedCondition<Boolean> 
{

	String expectedT;
	  String expectedxPath;
		
	  public customExpectedCondition(String expectedTitle, String expectedxPath) {
	    this.expectedT = expectedTitle;	
	    this.expectedxPath = expectedxPath;
	  }

	@Override
	public Boolean apply(WebDriver driver) 
	{
		String text=driver.findElement(By.xpath(expectedxPath)).getText();
		System.out.println(text);
		if(text.equalsIgnoreCase(expectedT))
		{
			return true;
		} else return false;
		
	}

}
