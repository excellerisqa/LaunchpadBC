package launchpadBC.testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_UserEntry;
import utils.CommonFunctions;
import utils.ObjectHelper;

public class VSTS_11418 extends BaseClass {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void verifySearchEntry(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11418");
			BaseClass.bh.getAllSteps(11418);
			System.out.println("VSTS_11418_User Entry started");
			CommonFunctions.waitForVisiblity(BC_MainPage.userEntryLink, 20);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.userEntryLink));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_UserEntry.userid_txt)
					.sendKeys(BaseClass.xlhandler.getDatabyCell(1, 1, 1));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_UserEntry.searchbtn));
			Thread.sleep(5000);
			if (!BC_UserEntry.verifySearchEntry() == true) {
				BaseClass.bh.addexpected(2);
			}
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11418", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}