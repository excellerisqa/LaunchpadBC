package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_UserEntry;
import launchpadBC.objects.BC_UserEntryCreation;
import utils.CommonFunctions;
import utils.ObjectHelper;
import utils.XLHandler;

@Test
public class VSTS_CreateAdminUser_13824 extends BaseClass_Regression {

	private static final Boolean False = null;

	@Parameters({ "bugflag", "exceptionflag" })
	public void CreateMOAUser(boolean bugflag, boolean exceptionflag) throws Exception {
		boolean exceptionError = false;

		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_13824");
			BaseClass_Regression.bh.getAllSteps(13824);
			System.out.println("VSTS_CreateAdminUser_13824 started");
			CommonFunctions.waitForVisiblity(BC_MainPage.userEntryLink, 20);

			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.userEntryLink));

			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");

			Thread.sleep(2000);

			CommonFunctions.clickUsingJavaExecutor(ObjectHelper.driver.findElement(BC_UserEntry.newButton));
			CommonFunctions.waitForVisiblity(BC_UserEntryCreation.userAttributeLink, 20);

			// String[] rowUserData = .readexcel(2, 3, "testdata2.xlsx"); // get testdata
			// from sheet
			String[] rowUserData = XLHandler.readexcel(2, 3, testDataFileName); // get testdata from sheet

			BC_UserEntryCreation.FillUserInformation(rowUserData);

			CommonFunctions.clickUsingJavaExecutor(BC_UserEntryCreation.userProfilesLink);
			Thread.sleep(2000);

			String[] rowProviderData = XLHandler.readexcel(2, 4, testDataFileName); // get testdata from sheet
			BC_UserEntryCreation.FillUserProfile(rowProviderData);

			// Click Add button to add profile
			CommonFunctions.clickUsingJavaExecutor(BC_UserEntryCreation.userProfileAddButton);
			Thread.sleep(2000);

			// Click Save button to save user
			CommonFunctions.clickUsingJavaExecutor(BC_UserEntryCreation.userSaveButton);

			String saveConfirmedinfo = ObjectHelper.driver.switchTo().alert().getText();
			Boolean isFound = False;
			isFound = saveConfirmedinfo.contains("The user has been saved successfully"); // true

			// String message = ObjectHelper.driver.switchTo().alert().getText();

			if (isFound) {
				ObjectHelper.driver.switchTo().alert().accept();

			} else {
				BaseClass_Regression.bh.addexpected(4);
			}

			/*
			 * String userId = message.substring(39, 45); // Thread.sleep(5000); // String
			 * userId = userEntryCreation.userID.getText();
			 * 
			 * ObjectHelper.driver.switchTo().defaultContent();
			 * 
			 * CommonFunctions.waitForVisiblity(BC_MainPage.userEntryLink, 20);
			 * Thread.sleep(5000);
			 * 
			 * JavascriptExecutor js2 = (JavascriptExecutor) ObjectHelper.driver;
			 * js2.executeScript("arguments[0].click();",
			 * ObjectHelper.driver.findElement(BC_MainPage.userEntryLink));
			 * 
			 * Thread.sleep(2000); ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			 * 
			 * ObjectHelper.driver.findElement(BC_UserEntry.userid_txt).sendKeys(userId);
			 * Thread.sleep(2000);
			 * 
			 * js.executeScript("arguments[0].click();",
			 * ObjectHelper.driver.findElement(BC_UserEntry.searchbtn)); Thread.sleep(5000);
			 * 
			 * if (!BC_UserEntry.verifySearchEntry() == true) {
			 * BaseClass_Regression.bh.addexpected(13); }
			 */
			Thread.sleep(9000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_13824",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}

}