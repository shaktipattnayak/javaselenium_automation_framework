package genericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BrowserSetUp {

	public static WebDriver base_Driver;
	
	public static WebDriver getBrowser(String browsername)
	{
		WebDriver driver = null;
		switch(browsername)
		{
			case "chrome":
				ChromeOptions options=new ChromeOptions();
				options.addArguments("disable-popup-blocking");
				options.setAcceptInsecureCerts(true);
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				options.addArguments("disable-infobars");
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/Drivers/chromedriver");
				driver = new ChromeDriver(options); 
				driver.manage().deleteAllCookies();
				break;
				
			case "canary":
				ChromeOptions canary_options=new ChromeOptions();
				canary_options.addArguments("disable-popup-blocking");
				canary_options.setAcceptInsecureCerts(true);
				canary_options.addArguments("test-type");
				canary_options.setBinary("C:\\Users\\Sibananda_G\\AppData\\Local\\Google\\Chrome SxS\\Application\\chrome.exe");
				canary_options.addArguments("start-maximized");
				canary_options.addArguments("disable-infobars");
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver(canary_options); 
				driver.manage().deleteAllCookies();
				break;
				
			case "incongnito":
				ChromeOptions incongnito_options=new ChromeOptions();
				incongnito_options.addArguments("disable-popup-blocking");
				incongnito_options.setAcceptInsecureCerts(true);
				incongnito_options.addArguments("test-type");
				incongnito_options.addArguments("start-maximized");
				incongnito_options.addArguments("disable-infobars");
				incongnito_options.addArguments("--incognito");
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver(incongnito_options); 
				driver.manage().deleteAllCookies();
				break;
			
			case "headless":
				ChromeOptions headless_options=new ChromeOptions();
				headless_options.addArguments("disable-popup-blocking");
				headless_options.setAcceptInsecureCerts(true);
				headless_options.addArguments("test-type");
				headless_options.addArguments("start-maximized");
				headless_options.addArguments("disable-infobars");
				headless_options.addArguments("--headless");
				headless_options.addArguments("--disable-gpu");
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver(headless_options); 
				driver.manage().deleteAllCookies();
				break;
				
			case "ie":
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				break;
				
		}
		return driver;
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		if(driver!=null)
		{
			driver.quit();
		}
	}
}