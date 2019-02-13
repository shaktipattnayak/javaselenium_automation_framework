package pageLibraries.CommonPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.BrowserSetUp;
import genericLibrary.TestUtil;

public class LogInPage extends BrowserSetUp {

	public LogInPage()
	{
		PageFactory.initElements(base_Driver, this);
	}
	
	@FindBy(xpath="//span[@class='logo']")
	private WebElement RaptorLogoInLogInPage;
	
	@FindBy(xpath="//li[text()='Invalid Username or Password']")
	private WebElement InvalidCredentialsMsg;
	
	@FindBy(id="Username")
	private WebElement UserNameField;
	
	@FindBy(id="Password")
	private WebElement PasswordField;
	
	@FindBy(xpath="//span[text()='Log in to Raptor']")
	private WebElement LoginBtn;
	@FindBy(xpath="//h4[text()='Welcome to Raptor']")
	 private WebElement  VideoPlayerHeader;	
	 
	 @FindBy(xpath="//button[text()='Continue to Raptor']")
	 private WebElement  ContinueButtonOnVideoPlayer;
	 
 
	public void UserLogIn(String userName,String password) throws Exception
	{
		TestUtil.waitUntilVisible(RaptorLogoInLogInPage);
		TestUtil.EnterText(UserNameField, userName);
		TestUtil.EnterText(PasswordField, password);
		TestUtil.clickElement(LoginBtn);
		if(TestUtil.getPageUrl().contains("Video"))
		{
			TestUtil.waitUntilVisible(VideoPlayerHeader);
			TestUtil.delayBy(101);
            TestUtil.clickElement(VideoPlayerHeader);
            TestUtil.pageDown();
            TestUtil.clickElement(ContinueButtonOnVideoPlayer);
		}
		
	}
	
	
}
