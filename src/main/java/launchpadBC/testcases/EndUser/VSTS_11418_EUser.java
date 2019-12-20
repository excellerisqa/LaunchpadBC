package launchpadBC.testcases.EndUser;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_UserEntry;
import utils.CommonFunctions;
import utils.ObjectHelper;

public class VSTS_11418_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void verifySearchEntry(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11418");
			BaseClass_EUser.bh.getAllSteps(11418);
			CommonFunctions.waitForVisiblity(BC_MainPage.userEntryLink, 20);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.userEntryLink));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_UserEntry.userid_txt)
					.sendKeys(BaseClass_EUser.xlhandler.getDatabyCell(1, 1, 1));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_UserEntry.searchbtn));
			Thread.sleep(5000);
			if (!BC_UserEntry.verifySearchEntry() == true) {
				BaseClass_EUser.bh.addexpected(2);
			}
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11418",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}