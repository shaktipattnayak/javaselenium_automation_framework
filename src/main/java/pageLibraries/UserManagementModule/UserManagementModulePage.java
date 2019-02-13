package pageLibraries.UserManagementModule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.model.Test;
import genericLibrary.BrowserSetUp;
import genericLibrary.TestUtil;


public class UserManagementModulePage extends BrowserSetUp{
	
	//Constructor
	// It is used to initialize elements of a Page class without having to use 'FindElement' or 'FindElements'.
	public UserManagementModulePage()
	{
		 PageFactory.initElements(base_Driver,this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Add Contractor')]/i")
	private WebElement AddUser_Button;
	
	@FindBy(id="manualEntryID")
	private WebElement ManualEntry;
	
	@FindBy(id="Person_FirstName")
	private WebElement User_FirstName;
	
	@FindBy(id="Person_LastName")
	private WebElement User_LastName;
	
	@FindBy(id="Person_DateOfBirth")
	private WebElement DateOfBirthField;
	
	@FindBy(id="CompanyId")
	private WebElement CompanyNameDropDown;
	
	@FindBy(id="OtherCompanyName")
	private WebElement OtherCompanyName;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement Save;

	@FindBy(xpath="//div[text()='The contractor was saved successfully']")
	private WebElement SuccessToastMsg;
	
	@FindBy(xpath="//div[text()='Sign in Successful']")
	private WebElement SuccessSignInToast;

	
	public boolean Addcontractors(String contractorFirstName,String contractorLastName,String DateOfBirth,String companyType,String otherCompanyName) throws Exception
	{
		TestUtil.clickElement(AddUser_Button);
		TestUtil.clickElement(ManualEntry);
		TestUtil.EnterText(User_FirstName,contractorFirstName);
		TestUtil.EnterText(User_LastName,contractorLastName);
		TestUtil.EnterText(DateOfBirthField,DateOfBirth);
		TestUtil.pageDown();
		TestUtil.selectByText(CompanyNameDropDown,companyType);
		TestUtil.EnterText(OtherCompanyName,otherCompanyName);
		TestUtil.clickElement(Save);
		return true;
	}
	public boolean VerifySuccessToast()
	{
		 TestUtil.waitUntilVisible(SuccessToastMsg);
		 return true;
	}
	public boolean SuccessSigninToast()
	{
		TestUtil.waitUntilVisible(SuccessSignInToast);
		 return true;
	}
 
}
