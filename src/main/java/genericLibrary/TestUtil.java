package genericLibrary;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
public class TestUtil extends BrowserSetUp{
	
	//++++++++++++++++WEBDRIVER EXPLICIT WAIT METHODS++++++++++++++++++++++++++++++
	
	public static WebDriverWait wait=new WebDriverWait(base_Driver,15);
	
	
	/*An expectation for checking that an element, known to be present on the DOM of a page, is visible.
	 Visibility means that the element is not only displayed but also has a height and width that is greater than 0. */
	
	public static void waitUntilVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void waitUntilVisible(String element_Xpath)
	{
		wait.until(ExpectedConditions.visibilityOf(getWebEelement(element_Xpath)));
	}
	
	//An expectation for checking an element is visible and enabled such that you can click it.
	public static void waitUntilClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void waitUntilClickable(String element_Xpath)
	{
		wait.until(ExpectedConditions.elementToBeClickable(getWebEelement(element_Xpath)));
	}
	
	
	public static void waitForAlertPresent()
	{
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public static void waitUntilAttribute(WebElement element,String attribute,String value)
	{
		wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
	}
	
	//++++++++++++++++END  WEBDRIVER EXPLICIT WAIT METHODS++++++++++++++++++++++++++++++
	
	//++++++++++++++++WEBDRIVER CUSTOM WAIT METHODS++++++++++++++++++++++++++++++
	
	public static void waitForElementDisplayed_Custom(WebElement element) throws Exception
	{
		for(int i=1;i<=20;i++)
		{
		if(base_Driver.findElements(getByType(element)).size()>0)
		{
			break;
		}
		else
		{
			delayBy(1);
		}
		}
	}
	
	public static void waitForElementDisplayed_Custom(String element_Xpath) throws Exception
	{
		for(int i=1;i<=15;i++)
		{
		if(base_Driver.findElements(By.xpath(element_Xpath)).size()>0)
		{
			break;
		}
		else
		{
			delayBy(1);
		}
		}
	}
	
	public static void waitForElementClickable_Custom(String element_Xpath) throws Exception
	{
		for(int j=1;j<=20;j++)
		{
		try
		{
			getWebEelement(element_Xpath).isEnabled();
			break;
		}
		catch(Exception CustomClickable)
		{
			delayBy(1);
		}
		}
	}
	public static void waitForElementClickable_Custom(WebElement element) throws Exception
	{
		for(int j=1;j<=20;j++)
		{
		try
		{
			element.isEnabled();
			break;
		}
		catch(Exception CustomClickable)
		{
			delayBy(1);
		}
		}
	}
	

	public static void waitForElement(WebElement element) throws Exception
	{
		waitUntilVisible(element);
		waitForElementDisplayed_Custom(element);
	}
	public static void waitForElement(String element_Xpath) throws Exception
	{
		waitUntilVisible(element_Xpath);
		waitForElementDisplayed_Custom(element_Xpath);
	}
	
	//++++++++++++++++END WEBDRIVER CUSTOM WAIT METHODS++++++++++++++++++++++++++++++
	//############### GENERAL TEST UTIL METHODS###########################################################
	
	
	//Returns by type object
	public static By getByType(WebElement element)
	{
		
		// By format = "[foundFrom] -> locator: term"
	    // see RemoteWebElement toString() implementation
		String flag=element.toString().split(" -> ")[1];
		String mainFlag="";
		for(int i=0;i<flag.length()-1;i++)
		{
			mainFlag=mainFlag+flag.charAt(i);
		}
		String[] data=mainFlag.split(": ");

	    String locator = data[0];
	    String term = data[1];

	    switch (locator) {
	    case "xpath":
	        return By.xpath(term);
	    case "css selector":
	        return By.cssSelector(term);
	    case "id":
	        return By.id(term);
	    case "tag name":
	        return By.tagName(term);
	    case "name":
	        return By.name(term);
	    case "link text":
	        return By.linkText(term);
	    case "class name":
	        return By.className(term);
	    }
	    return (By) element;
	}
	
	//Is this element displayed or not? This method avoids the problem of having to parse an element's "style" attribute.
	public static boolean isElementDisplayed(WebElement element)
	{
		try {return element.isDisplayed();}catch(NoSuchElementException ex) {return false;}
	}
	public static boolean isElementDisplayed(String element_Xpath)
	{
		try {return getWebEelement(element_Xpath).isDisplayed();}catch(NoSuchElementException ex) {return false;}
	}
	
	public static void pageUp()
	{
		new Actions(base_Driver).sendKeys(Keys.PAGE_UP).perform();
	}
	
	public static void pageDown()
	{
		new Actions(base_Driver).sendKeys(Keys.PAGE_DOWN).perform();
	}
	
	public static void moveToElement(WebElement element)
	{
		new Actions(base_Driver).moveToElement(element).build().perform();
	}
	
	public static void moveToElement(String element_Xpath)
	{
		new Actions(base_Driver).moveToElement(getWebEelement(element_Xpath)).build().perform();
	}
	public static void elementMouseClick(WebElement element)
	{
		new Actions(base_Driver).click(element);
	}
	public static void elementMouseClick(String element_Xpath)
	{
		new Actions(base_Driver).click(getWebEelement(element_Xpath));
	}
	
	public static WebElement getWebEelement(String element_Xpath)
	{
		return base_Driver.findElement(By.xpath(element_Xpath));
	}
	
	public static String captureScreenShot(String ScreenShotName)
	{
		try{
			TakesScreenshot ts=(TakesScreenshot)base_Driver;
			File Source = ts.getScreenshotAs(OutputType.FILE);
			String dest=System.getProperty("user.dir")+"\\Screenshots\\"+ScreenShotName+".png";
			File destination = new File(dest);
			FileUtils.copyFile(Source, destination);
			System.out.println("Screen shot taken");
			return dest;
		}
		catch (Exception e){
			System.out.println("Exception while capturing screen shot"+e.getMessage());
			return e.getMessage();
		}
	}
	
	public static void pageRefresh()
	{
		base_Driver.navigate().refresh();
	}
	
	public static void clickElement(WebElement element) throws Exception
	{
		waitForElement(element);
		waitUntilClickable(element);
		waitForElementClickable_Custom(element);
		element.click();
	}
	public static boolean waitForJQueryProcessing() { 
        boolean jQcondition = false; 
        try { 
        	base_Driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify 
                                                                            // implicitlyWait() 
            new WebDriverWait(base_Driver, 30) { 
            }.until(new ExpectedCondition<Boolean>() { 
 
                public Boolean apply(WebDriver driverObject) { 
                    return (Boolean) ((JavascriptExecutor) driverObject) 
                            .executeScript("return jQuery.active == 0"); 
 
                } 
            }); 
            jQcondition = (Boolean) ((JavascriptExecutor) base_Driver) 
                    .executeScript("return jQuery.active == 0");  
            base_Driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // reset 
                                                                                // implicitlyWait 
            return jQcondition; 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return jQcondition; 
	}
	
	
	public static void clickElement(String element_Xpath) throws Exception
	{
		waitForElement(element_Xpath);
		waitUntilClickable(element_Xpath);
		waitForElementClickable_Custom(element_Xpath);
		getWebEelement(element_Xpath).click();
	}
	public static String getPageUrl()
	{
		return base_Driver.getCurrentUrl();
	}
	
	
	
	public static void EnterText(WebElement element,String text) throws Exception
	{
		waitForElement(element);
		element.clear();
		element.sendKeys(text);
	}
	public static void EnterText(String element_Xpath,String text) throws Exception
	{
		waitForElement(element_Xpath);
		getWebEelement(element_Xpath).clear();
		getWebEelement(element_Xpath).sendKeys(text);
	}
	
	public static void selectByText(WebElement element,String text) throws Exception
	{
		waitForElement(element);
		new Select(element).selectByVisibleText(text);
	}
	
	public static void selectByText(String element_Xpath,String text) throws Exception
	{
		waitForElement(element_Xpath);
		new Select(getWebEelement(element_Xpath)).selectByVisibleText(text);
	}
	
	public static void selectByIndex(WebElement element,int index) throws Exception
	{
		waitForElement(element);
		new Select(element).selectByIndex(index);
	}
	
	public static void selectByIndex(String element_Xpath,int index) throws Exception
	{
		waitForElement(element_Xpath);
		new Select(getWebEelement(element_Xpath)).selectByIndex(index);
	}
	
	public static void selectByValue(WebElement element,String value) throws Exception
	{
		waitForElement(element);
		new Select(element).selectByValue(value);
	}
	
	public static void selectByValue(String element_Xpath,String value) throws Exception
	{
		waitForElement(element_Xpath);
		new Select(getWebEelement(element_Xpath)).selectByVisibleText(value);
	}
	public static void delayBy(int timeInSec) throws Exception
	{
		Thread.sleep(timeInSec*1000);
	}
	
	public static String getAttribute(WebElement element,String attributeName)
	{
		return element.getAttribute(attributeName);
	}
	
	
	public static void acceptAlert()
	{
		waitForAlertPresent();
		base_Driver.switchTo().alert().accept();
	}
	public static int getElementsSize(WebElement element)
	{
		return base_Driver.findElements(getByType(element)).size();
	}
	public static int getElementsSize(String element_Xpath)
	{
		return base_Driver.findElements(By.xpath(element_Xpath)).size();
	}
	
	public static void scrollToElement(WebElement element)
	{
		Point p=element.getLocation();
		JavascriptExecutor js=(JavascriptExecutor)base_Driver;
		js.executeScript("Window.ScrollBy("+p.x+","+p.y+")","" );
			
			
	}
	
	
}
