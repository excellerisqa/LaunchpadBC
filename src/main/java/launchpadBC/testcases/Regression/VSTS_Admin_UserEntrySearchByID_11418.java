package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_UserEntry;
import utils.CommonFunctions;
import utils.ObjectHelper;

public class VSTS_Admin_UserEntrySearchByID_11418 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void verifySearchEntry(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11418");
			BaseClass_Regression.bh.getAllSteps(11418);
			System.out.println("VSTS_Admin_UserEntrySearchByID_11418 started");
			CommonFunctions.waitForVisiblity(BC_MainPage.userEntryLink, 20);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.userEntryLink));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_UserEntry.userid_txt)
					.sendKeys(BaseClass_Regression.xlhandler.getDatabyCell(2, 18, 1));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_UserEntry.searchbtn));
			Thread.sleep(5000);
			if (!BC_UserEntry.verifySearchEntry() == true) {
				BaseClass_Regression.bh.addexpected(2);
			}
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11418",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}