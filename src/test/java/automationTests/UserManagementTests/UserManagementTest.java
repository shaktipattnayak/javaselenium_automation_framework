package automationTests.UserManagementTests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import automationTests.BaseTest;
import genericLibrary.DataUtil;
import pageLibraries.UserManagementModule.*;

public class UserManagementTest extends BaseTest{

	@DataProvider
	public Object[][] Addcontractors_ManuallyData()
	{
		return DataUtil.getData("Addcontractors_Manually","ContractorTestData", xls);
	}
	
	@Test(enabled=false,dataProvider="Addcontractors_ManuallyData")
	public void Addcontractors_Manually(Hashtable<String,String>data) throws Exception
	{

		UserManagementModulePage contractor=new UserManagementModulePage();
		Assert.assertTrue(contractor.Addcontractors(data.get("ContractorFirstName"),data.get("ContractorLastName"),data.get("DateOfBirth"),data.get("CompanyType"),data.get("OtherCompanyName")));
		Assert.assertTrue(contractor.VerifySuccessToast());

	}
	
}
